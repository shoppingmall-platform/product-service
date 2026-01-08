package com.smplatform.product_service.domain.order.service.impl;

import com.smplatform.product_service.domain.cart.repository.CartItemRepository;
import com.smplatform.product_service.domain.coupon.dto.MemberCouponResponseDto;
import com.smplatform.product_service.domain.coupon.entity.Coupon;
import com.smplatform.product_service.domain.coupon.exception.CouponNotFoundException;
import com.smplatform.product_service.domain.coupon.repository.CouponRepository;
import com.smplatform.product_service.domain.coupon.service.MemberCouponService;
import com.smplatform.product_service.domain.discount.repository.DiscountRepository;
import com.smplatform.product_service.domain.member.entity.Delivery;
import com.smplatform.product_service.domain.member.entity.Member;
import com.smplatform.product_service.domain.member.repository.DeliveryRepository;
import com.smplatform.product_service.domain.member.repository.MemberRepository;
import com.smplatform.product_service.domain.order.dto.OrderRequestDto;
import com.smplatform.product_service.domain.order.dto.OrderResponseDto;
import com.smplatform.product_service.domain.order.entity.Order;
import com.smplatform.product_service.domain.order.entity.OrderProduct;
import com.smplatform.product_service.domain.order.entity.OrderProductStatus;
import com.smplatform.product_service.domain.order.entity.OrderStatus;
import com.smplatform.product_service.domain.order.repository.OrderBenefitRepository;
import com.smplatform.product_service.domain.order.repository.OrderProductRepository;
import com.smplatform.product_service.domain.order.repository.OrderRepository;
import com.smplatform.product_service.domain.order.service.OrderService;
import com.smplatform.product_service.domain.product.exception.ProductNotFoundException;
import com.smplatform.product_service.domain.product.exception.ProductOptionNotFoundException;
import com.smplatform.product_service.domain.product.repository.ProductOptionRepository;
import com.smplatform.product_service.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final DiscountRepository discountRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final DeliveryRepository deliveryRepository;
    private final MemberRepository memberRepository;
    private final OrderProductRepository orderProductRepository;
    private final OrderBenefitRepository orderBenefitRepository;
    private final CouponRepository couponRepository;
    private final MemberCouponService memberCouponService;

    @Override
    public Long saveOrder(String memberId, OrderRequestDto.OrderSave requestDto) {
        Map<Long, OrderRequestDto.OrderItem> requestDtoMap = new HashMap<>();
        Map<Long, OrderResponseDto.ProductOptionFlatDto> savedDtoMap = new HashMap<>();

        Member member = memberRepository.findById(memberId).orElse(null);

        List<OrderResponseDto.ProductOptionFlatDto> productOptionFlatDtos = orderRepository.findAllByProductOptionIds(
                requestDto.getItems().stream().map(orderItem -> {
                    requestDtoMap.put(orderItem.getProductOptionId(), orderItem);
                    return orderItem.getProductOptionId();
                }).toList()
        );

        // 할인 정보 불러오기, 실제 제품에 할인 가격 적용
        AtomicInteger originalTotalPrice = new AtomicInteger();
        AtomicInteger discountedTotalPrice = new AtomicInteger();
        productOptionFlatDtos.forEach(unit -> {
            if (requestDtoMap.get(unit.getProductOptionId()).getQuantity() > unit.getStockQuantity()) {
                throw new IllegalArgumentException();
            }

            originalTotalPrice.addAndGet((unit.getPrice() + unit.getAdditionalPrice())
                    * requestDtoMap.get(unit.getProductOptionId()).getQuantity());

            if (!Objects.isNull(unit.getDiscountId())) {
                int unitPrice = productRepository.findByProductId(unit.getProductId())
                        .orElseThrow(() -> new ProductNotFoundException("product not found"))
                        .getDiscountedPrice() + unit.getAdditionalPrice();
                System.out.println("이거슨 unit price : " + unitPrice);
                unit.setUnitTotalPrice(unitPrice);
                discountedTotalPrice.addAndGet(unitPrice * requestDtoMap.get(unit.getProductOptionId()).getQuantity());
            } else {
                unit.setUnitTotalPrice(unit.getPrice() + unit.getAdditionalPrice());
                discountedTotalPrice.addAndGet(
                        (unit.getPrice() + unit.getAdditionalPrice())
                                * requestDtoMap.get(unit.getProductOptionId()).getQuantity());
            }
            savedDtoMap.put(unit.getProductOptionId(), unit);
        });

        int productDiscount = originalTotalPrice.get() - discountedTotalPrice.get();
        int couponDiscount = 0;
        int pointDiscount = 0;

        // 사용자 쿠폰 및 포인트 불러오기, 쿠폰 및 포인트 적용 가능한지 검증
        // todo additionalDiscount가 요청오는대로 할인하고있음. 수정 필수(사용가능한 할인인지, 쿠포은 완료)
        if (requestDto.getOrderDiscount() != null && (requestDto.getOrderDiscount().getCouponId() != null || requestDto.getOrderDiscount().getPoints() != null)) {
            if (!memberCouponService.getCoupons(memberId).stream()
                    .map(MemberCouponResponseDto.MemberCouponInfo::getCouponId).toList()
                    .contains(requestDto.getOrderDiscount().getCouponId())) {
                throw new CouponNotFoundException(
                        String.format("%s 회원님은 %s 쿠폰을 가지고 있지 않습니다.", memberId, requestDto.getOrderDiscount().getCouponId())
                );
            }
            Coupon coupon = couponRepository.findById(requestDto.getOrderDiscount().getCouponId())
                    .orElseThrow(() -> new CouponNotFoundException(String.format("coupon : %s not found", requestDto.getOrderDiscount().getCouponId())));

            if (!coupon.isAvailable()) {
                throw new IllegalArgumentException("coupon 사용 가능 기간을 넘거나 도달 하지 못했습니다.");
            }
            couponDiscount = coupon.calculateDiscountedPrice(discountedTotalPrice.get());
        }

        // 실제 가격 계산 및 검증
        int finalPrice = discountedTotalPrice.get() - couponDiscount - pointDiscount + requestDto.getOrderDetail().getShippingFee();

        System.out.println("이거시 진짜 최종 가격 : " + finalPrice);
        if (requestDto.getOrderDetail().getFinalAmount() != finalPrice) {
            throw new IllegalArgumentException("주문 가격이 일치하지 않습니다");
        }

        String orderTitle = productOptionRepository.findById(requestDto.getItems().get(0).getProductOptionId())
                .orElseThrow(() -> new ProductOptionNotFoundException(String.format("제품 %s이 존재하지 않습니다.", requestDto.getItems().get(0).getProductOptionId())))
                .getProduct().getName();

        if (requestDto.getItems().size() > 1) {
            orderTitle = String.format("%s 외 %s 종", orderTitle, requestDto.getItems().size());
        }

        // 할인 및 배송 엔티티 저장
        Order order = orderRepository.save(
                new Order(null, member, null, finalPrice, OrderStatus.PROGRESSING, orderTitle)
        );
        Delivery delivery = deliveryRepository.save(
                new Delivery(
                        0L,
                        null,
                        null,
                        null,
                        requestDto.getAddressInfo().getPostalCode(),
                        requestDto.getAddressInfo().getAddress1(),
                        requestDto.getAddressInfo().getAddress2(),
                        requestDto.getAddressInfo().getReceiver(),
                        requestDto.getAddressInfo().getPhoneNumber()
                )
        );

        // 장바구니 아이템이라면 장바구니 아이템 삭제
        requestDto.getItems().forEach(orderItem -> {
            if (!Objects.isNull(orderItem.getCartItemId())) {
                cartItemRepository.deleteById(orderItem.getCartItemId());
            }
            orderProductRepository.save(
                    new OrderProduct(
                            null,
                            order,
                            delivery,
                            orderItem.getQuantity(),
                            savedDtoMap.get(orderItem.getProductOptionId()).getUnitTotalPrice(),
                            OrderProductStatus.PAYMENT_PENDING,
                            savedDtoMap.get(orderItem.getProductOptionId()).getProductId(),
                            savedDtoMap.get(orderItem.getProductOptionId()).getProductOptionId(),
                            savedDtoMap.get(orderItem.getProductOptionId()).getProductOptionName(),
                            savedDtoMap.get(orderItem.getProductOptionId()).getName(),
                            savedDtoMap.get(orderItem.getProductOptionId()).getDiscountId(),
                            savedDtoMap.get(orderItem.getProductOptionId()).getDiscountType(),
                            savedDtoMap.get(orderItem.getProductOptionId()).getDiscountValue()
                    )
            );
        });

        return order.getOrderId();
    }
}
