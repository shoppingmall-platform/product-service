package com.smplatform.product_service.domain.order.service.impl;

import com.smplatform.product_service.domain.cart.repository.CartItemRepository;
import com.smplatform.product_service.domain.discount.repository.DiscountRepository;
import com.smplatform.product_service.domain.member.entity.Delivery;
import com.smplatform.product_service.domain.member.entity.Member;
import com.smplatform.product_service.domain.member.repository.AddressRepository;
import com.smplatform.product_service.domain.member.repository.DeliveryRepository;
import com.smplatform.product_service.domain.member.repository.MemberRepository;
import com.smplatform.product_service.domain.order.dto.OrderRequestDto;
import com.smplatform.product_service.domain.order.dto.OrderResponseDto;
import com.smplatform.product_service.domain.order.entity.Order;
import com.smplatform.product_service.domain.order.entity.OrderStatus;
import com.smplatform.product_service.domain.order.repository.OrderRepository;
import com.smplatform.product_service.domain.order.service.OrderService;
import com.smplatform.product_service.domain.product.exception.ProductOptionNotFoundException;
import com.smplatform.product_service.domain.product.repository.ProductOptionRepository;
import com.smplatform.product_service.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final DiscountRepository discountRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final AddressRepository addressRepository;
    private final DeliveryRepository deliveryRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long saveOrder(String memberId, OrderRequestDto.OrderSave requestDto) {
        // 회원
        // 비회원
        // 주문수량 재고 확인
        // 장바구니 구매
        // 바로 구매
        // 저장된 배송지가 아닌 새 배송지(저장x) 구매
        // 저장된 배송지로 구매
        // 추가 가격은 할인 적용 후 더하기
        Map<Long, OrderRequestDto.OrderItem> map = new HashMap<>();
        Member member = memberRepository.findById(memberId).orElse(null);

        List<OrderResponseDto.ProductOptionFlatDto> productOptionFlatDtos = orderRepository.findAllByProductOptionIds(
                requestDto.getItems().stream().map(orderItem -> {
                    map.put(orderItem.getProductOptionId(), orderItem);
                    return orderItem.getProductOptionId();
                }).toList()
        );

        productOptionFlatDtos.forEach(productOptionFlatDto -> {
            if (map.get(productOptionFlatDto.getProductOptionId()).getQuantity() > productOptionFlatDto.getStockQuantity()) {
                throw new IllegalArgumentException();
            }
        });

        // 장바구니 아이템이라면 장바구니 아이템 삭제
        requestDto.getItems().forEach(orderItem -> {
            if (!Objects.isNull(orderItem.getCartItemId())) {
                cartItemRepository.deleteById(orderItem.getCartItemId());
            }
        });

        String orderTitle = productOptionRepository.findById(requestDto.getItems().get(0).getProductOptionId())
                .orElseThrow(() -> new ProductOptionNotFoundException(String.format("제품 %s이 존재하지 않습니다.", requestDto.getItems().get(0).getProductOptionId())))
                .getProduct().getName();

        if (requestDto.getItems().size() > 1) {
            orderTitle = String.format("%s 외 %s 종", orderTitle, requestDto.getItems().size());
        }

        orderRepository.save(new Order(null, member, null, null, OrderStatus.PROGRESSING, orderTitle));
        // 저장되지 않은 새배송지의 경우
        if (Objects.isNull(requestDto.getAddressInfo().getAddressId())) {
        } else {
        }
        // 계산검증


        return null;
    }
}
