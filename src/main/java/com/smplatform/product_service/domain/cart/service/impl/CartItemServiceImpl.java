package com.smplatform.product_service.domain.cart.service.impl;

import com.smplatform.product_service.domain.cart.dto.CartItemRequestDto;
import com.smplatform.product_service.domain.cart.dto.CartItemResponseDto;
import com.smplatform.product_service.domain.cart.entity.CartItem;
import com.smplatform.product_service.domain.cart.exception.CartItemNotFoundException;
import com.smplatform.product_service.domain.cart.exception.CartItemOptionAlreadyExistsException;
import com.smplatform.product_service.domain.cart.repository.CartItemRepository;
import com.smplatform.product_service.domain.cart.service.CartItemService;
import com.smplatform.product_service.domain.discount.entity.Discount;
import com.smplatform.product_service.domain.member.entity.Member;
import com.smplatform.product_service.domain.member.exception.MemberNotFoundException;
import com.smplatform.product_service.domain.member.repository.MemberRepository;
import com.smplatform.product_service.domain.product.entity.ProductOption;
import com.smplatform.product_service.domain.product.exception.ProductOptionNotFoundException;
import com.smplatform.product_service.domain.product.repository.ProductOptionDetailRepository;
import com.smplatform.product_service.domain.product.repository.ProductOptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final MemberRepository memberRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductOptionDetailRepository productOptionDetailRepository;

    @Override
    public String addCartItems(String memberId, List<CartItemRequestDto.CartAdd> requestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));

        List<CartItem> items = new LinkedList<>();
        for (CartItemRequestDto.CartAdd itemDto : requestDto) {
            ProductOption productOption = productOptionRepository.findById(itemDto.getProductOptionId())
                    .orElseThrow(() -> new ProductOptionNotFoundException(itemDto.getProductOptionId()));

            // 이미 존재하는 상품옵션이면 수량 +1
            Optional<CartItem> optionalCartItem = cartItemRepository.findByMemberAndProductOption(member, productOption);
            if (optionalCartItem.isPresent()) {
                CartItem cartItem = optionalCartItem.get();
                cartItem.addQuantity(itemDto.getQuantity());
            }

            // 장바구니 상품 추가
            else {
                items.add(CartItem.createCartItem(member, productOption, itemDto.getQuantity()));
            }
        }

        cartItemRepository.saveAll(items);

        return "success";
    }

    @Override
    public String updateCartItems(String memberId, List<CartItemRequestDto.CartUpdate> requestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));

        for (CartItemRequestDto.CartUpdate item : requestDto) {
            CartItem cartItem = cartItemRepository.findById(item.getCartItemId())
                    .orElseThrow(() -> new CartItemNotFoundException(item.getCartItemId()));

            ProductOption productOption = productOptionRepository.findById(item.getProductOptionId())
                    .orElseThrow(() -> new ProductOptionNotFoundException(item.getProductOptionId()));

            if (cartItemRepository.findByMemberAndProductOption(member, productOption).isPresent()) {
                throw new CartItemOptionAlreadyExistsException(item.getProductOptionId());
            }
            cartItem.updateOption(productOption, item.getQuantity());
        }

        return "success";
    }

    @Override
    public Void deleteCartItems(String memberId, List<CartItemRequestDto.CartDelete> requestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));

        cartItemRepository.deleteAllByIdInBatch(requestDto.stream().map(CartItemRequestDto.CartDelete::getCartItemId).toList());
        return null;
    }

    @Override
    public CartItemResponseDto.CartGet getCartItems(String memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(String.format("멤버 %s 를 찾을 수 없습니다", memberId)));
        List<CartItemResponseDto.CartItemFlatDto> allCartItemsByMemberId = cartItemRepository.findAllByMemberId(member.getMemberId());
        log.error("페치 결과 : {}", allCartItemsByMemberId);
        Map<Long, CartItemResponseDto.CartItemGet> cartMap = new LinkedHashMap<>();
        LocalDateTime now = LocalDateTime.now();

        for (CartItemResponseDto.CartItemFlatDto f : allCartItemsByMemberId) {
            CartItemResponseDto.CartItemGet cartItemGet = cartMap.computeIfAbsent(f.getCartItemId(), id ->
                    CartItemResponseDto.CartItemGet.builder()
                            .cartItemId(id)
                            .quantity(f.getQuantity())
                            .productOptionInfo(
                                    CartItemResponseDto.ProductOptionInfo.builder()
                                            .productOptionId(f.getProductOptionId())
                                            .productInfo(
                                                    CartItemResponseDto.ProductInfo.builder()
                                                            .productId(f.getProductId())
                                                            .name(f.getName())
                                                            .price(f.getPrice())
                                                            .productOptions(new ArrayList<>())
                                                            .build()
                                            )
                                            .build()
                            )
                            .build());

            CartItemResponseDto.ProductInfo productInfo = cartItemGet.getProductOptionInfo().getProductInfo();

            CartItemResponseDto.ProductOptionGet productOptionGet = productInfo.getProductOptions().stream()
                    .filter(o -> o.getProductOptionId().equals(f.getProductOptionId()))
                    .findFirst()
                    .orElseGet(() -> {
                        CartItemResponseDto.ProductOptionGet o = CartItemResponseDto.ProductOptionGet.builder()
                                .productOptionId(f.getProductOptionId())
                                .productOptionName(f.getProductOptionName())
                                .stockQuantity(f.getStockQuantity())
                                .additionalPrice(f.getAdditionalPrice())
                                .productOptionDetails(new ArrayList<>())
                                .build();
                        productInfo.getProductOptions().add(o);
                        return o;
                    });

            /* ────── 3) 옵션Detail ────── */
            if (f.getProductOptionType() != null) {
                productOptionGet.getProductOptionDetails().add(
                        CartItemResponseDto.ProductOptionDetailGet.builder()
                                .productOptionType(f.getProductOptionType())
                                .productOptionDetailName(f.getProductOptionDetailName())
                                .build());
            }

            /* ────── 4) 할인 계산 (ProductInfo 한 번만) ────── */
            if (productInfo.getDiscountedPrice() == null) {            // 아직 계산 안했을 때만
                int discounted = calculateDiscountedPrice(
                        f.getPrice(),
                        f.getDiscountType(),
                        f.getDiscountValue(),
                        f.getDiscountStartDate(),
                        f.getDiscountEndDate(),
                        now);
                productInfo.setDiscountedPrice(discounted);
            }
        }

        return new CartItemResponseDto.CartGet(new ArrayList<>(cartMap.values()));
    }

    private int calculateDiscountedPrice(int price,
                                         Discount.Type type,
                                         Integer value,
                                         LocalDateTime start,
                                         LocalDateTime end,
                                         LocalDateTime now) {

        if (type == null || value == null) return price;
        if (start == null || end == null || now.isBefore(start) || now.isAfter(end))
            return price;

        return switch (type) {
            case RATE -> price - (price * value / 100);
            case AMOUNT -> price - value;
        };
    }
}
