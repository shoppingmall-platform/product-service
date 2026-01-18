package com.smplatform.product_service.domain.coupon.service.impl;

import com.smplatform.product_service.domain.coupon.dto.CouponRequestDto;
import com.smplatform.product_service.domain.coupon.dto.CouponResponseDto;
import com.smplatform.product_service.domain.coupon.entity.Coupon;
import com.smplatform.product_service.domain.coupon.entity.CouponApplyPolicy;
import com.smplatform.product_service.domain.coupon.entity.CouponApplyTarget;
import com.smplatform.product_service.domain.coupon.entity.CouponApplyType;
import com.smplatform.product_service.domain.coupon.entity.CouponIssuePolicy;
import com.smplatform.product_service.domain.coupon.entity.CouponIssueTargetMember;
import com.smplatform.product_service.domain.coupon.entity.IssueTargetType;
import com.smplatform.product_service.domain.coupon.entity.IssueType;
import com.smplatform.product_service.domain.coupon.entity.MemberCoupon;
import com.smplatform.product_service.domain.coupon.repository.CouponApplyPolicyRepository;
import com.smplatform.product_service.domain.coupon.repository.CouponApplyTargetRepository;
import com.smplatform.product_service.domain.coupon.repository.CouponIssuePolicyRepository;
import com.smplatform.product_service.domain.coupon.repository.CouponIssueTargetMemberRepository;
import com.smplatform.product_service.domain.coupon.repository.CouponRepository;
import com.smplatform.product_service.domain.coupon.repository.MemberCouponRepository;
import com.smplatform.product_service.domain.coupon.service.CouponService;
import com.smplatform.product_service.domain.member.entity.Member;
import com.smplatform.product_service.domain.member.repository.MemberRepository;
import com.smplatform.product_service.exception.BadRequestException;
import com.smplatform.product_service.exception.InternalServerErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final CouponIssuePolicyRepository couponIssuePolicyRepository;
    private final CouponIssueTargetMemberRepository couponIssueTargetMemberRepository;
    private final CouponApplyPolicyRepository couponApplyPolicyRepository;
    private final CouponApplyTargetRepository couponApplyTargetRepository;
    private final MemberRepository memberRepository;
    private final MemberCouponRepository memberCouponRepository;

    @Override
    public String createCoupon(CouponRequestDto.CouponCreate couponRequestDto) {
        // 입력값 검증
        if (couponRequestDto == null) {
            throw new BadRequestException("쿠폰 생성 요청 정보가 누락되었습니다.");
        }

        if (couponRequestDto.getIssueType() == IssueType.CODE && (couponRequestDto.getCouponIssueCode() == null || couponRequestDto.getCouponIssueCode().isBlank())) {
            throw new BadRequestException("쿠폰 코드를 입력해주세요.");
        }

        if (couponRequestDto.getCouponName() == null || couponRequestDto.getCouponName().isBlank()) {
            throw new BadRequestException("쿠폰 이름을 입력해주세요.");
        }

        if (couponRequestDto.getAmount() == null || couponRequestDto.getAmount() <= 0) {
            throw new BadRequestException("할인 금액은 0보다 커야 합니다.");
        }

        IssueTargetType issueTargetType = resolveIssueTargetType(couponRequestDto);
        CouponApplyType applyType = resolveApplyType(couponRequestDto);

        validateIssueTargets(couponRequestDto, issueTargetType);
        validateApplyTargets(couponRequestDto, applyType);

        try {
            Coupon coupon = Coupon.createCoupon(couponRequestDto);
            couponRepository.save(coupon);

            CouponIssuePolicy issuePolicy = CouponIssuePolicy.create(coupon, couponRequestDto.getIssueType(), issueTargetType);
            couponIssuePolicyRepository.save(issuePolicy);
            saveIssueTargets(issuePolicy, couponRequestDto);

            CouponApplyPolicy applyPolicy = CouponApplyPolicy.create(coupon, applyType);
            couponApplyPolicyRepository.save(applyPolicy);
            saveApplyTargets(applyPolicy, couponRequestDto);

            if (couponRequestDto.getIssueType() == IssueType.AUTO) {
                issueAutoCoupons(coupon, issueTargetType, couponRequestDto);
            }

            log.info("쿠폰이 성공적으로 생성되었습니다. 쿠폰ID: {}, 쿠폰코드: {}", coupon.getCouponId(), coupon.getCouponCode());
            return String.valueOf(coupon.getCouponId());
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerErrorException("쿠폰 생성에 실패했습니다.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CouponResponseDto.CouponInfo> getCouponList(CouponRequestDto.CouponSearch couponSearchDto) {
        try {
            if (couponSearchDto == null) {
                log.info("조건 없이 전체 쿠폰 목록 조회");
                return couponRepository.searchCoupon(null).stream()
                        .map(CouponResponseDto.CouponInfo::of)
                        .collect(Collectors.toList());
            }

            log.info("쿠폰 목록 검색 - 검색 조건: {}", couponSearchDto);
            return couponRepository.searchCoupon(couponSearchDto).stream()
                    .map(CouponResponseDto.CouponInfo::of)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new InternalServerErrorException("쿠폰 목록 조회에 실패했습니다.");
        }
    }

    @Override
    public void deleteCoupon(CouponRequestDto.CouponDelete couponRequestDto) {
        if (couponRequestDto == null || couponRequestDto.getCouponId() == null) {
            throw new BadRequestException("삭제할 쿠폰 ID가 누락되었습니다.");
        }

        try {
            Long couponId = couponRequestDto.getCouponId();

            if (!couponRepository.existsById(couponId)) {
                throw new BadRequestException("존재하지 않는 쿠폰입니다.");
            }

            couponRepository.deleteById(couponId);
            log.info("쿠폰이 성공적으로 삭제되었습니다. 쿠폰ID: {}", couponId);

        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException("쿠폰 삭제 중 비즈니스 로직 오류");
        } catch (Exception e) {
            throw new InternalServerErrorException("쿠폰 삭제에 실패했습니다.");
        }
    }

    private IssueTargetType resolveIssueTargetType(CouponRequestDto.CouponCreate couponRequestDto) {
        if (couponRequestDto.getIssueType() == IssueType.AUTO) {
            return Objects.requireNonNullElse(couponRequestDto.getIssueTargetType(), IssueTargetType.ALL);
        }
        return Objects.requireNonNullElse(couponRequestDto.getIssueTargetType(), IssueTargetType.ALL);
    }

    private CouponApplyType resolveApplyType(CouponRequestDto.CouponCreate couponRequestDto) {
        return Objects.requireNonNullElse(couponRequestDto.getApplyType(), CouponApplyType.ALL_PRODUCTS);
    }

    private void validateIssueTargets(CouponRequestDto.CouponCreate couponRequestDto, IssueTargetType issueTargetType) {
        if (couponRequestDto.getIssueType() != IssueType.AUTO) {
            return;
        }
        if (issueTargetType == IssueTargetType.MEMBERS) {
            List<String> memberIds = couponRequestDto.getIssueTargetMemberIds();
            if (memberIds == null || memberIds.isEmpty()) {
                throw new BadRequestException("자동 발급 대상 회원 ID 목록이 필요합니다.");
            }
        }
    }

    private void validateApplyTargets(CouponRequestDto.CouponCreate couponRequestDto, CouponApplyType applyType) {
        if (applyType == CouponApplyType.ALL_PRODUCTS) {
            return;
        }
        List<Long> targetIds = couponRequestDto.getApplyTargetIds();
        if (targetIds == null || targetIds.isEmpty()) {
            throw new BadRequestException("적용 대상 ID 목록이 필요합니다.");
        }
    }

    private void saveIssueTargets(CouponIssuePolicy issuePolicy, CouponRequestDto.CouponCreate couponRequestDto) {
        if (issuePolicy.getTargetType() != IssueTargetType.MEMBERS) {
            return;
        }
        List<String> memberIds = couponRequestDto.getIssueTargetMemberIds();
        if (memberIds == null || memberIds.isEmpty()) {
            return;
        }
        List<CouponIssueTargetMember> targets = memberIds.stream()
                .map(memberId -> CouponIssueTargetMember.create(issuePolicy, memberId))
                .toList();
        couponIssueTargetMemberRepository.saveAll(targets);
    }

    private void saveApplyTargets(CouponApplyPolicy applyPolicy, CouponRequestDto.CouponCreate couponRequestDto) {
        if (applyPolicy.getApplyType() == CouponApplyType.ALL_PRODUCTS) {
            return;
        }
        List<Long> targetIds = couponRequestDto.getApplyTargetIds();
        if (targetIds == null || targetIds.isEmpty()) {
            return;
        }
        List<CouponApplyTarget> targets = targetIds.stream()
                .map(targetId -> CouponApplyTarget.create(applyPolicy, targetId))
                .toList();
        couponApplyTargetRepository.saveAll(targets);
    }

    private void issueAutoCoupons(Coupon coupon, IssueTargetType issueTargetType, CouponRequestDto.CouponCreate couponRequestDto) {
        List<Member> members;
        if (issueTargetType == IssueTargetType.ALL) {
            members = memberRepository.findAll();
        } else {
            List<String> memberIds = couponRequestDto.getIssueTargetMemberIds();
            members = memberRepository.findAllById(memberIds);
            if (memberIds != null && members.size() != memberIds.size()) {
                Set<String> foundIds = members.stream().map(Member::getMemberId).collect(Collectors.toSet());
                String missingId = memberIds.stream().filter(id -> !foundIds.contains(id)).findFirst().orElse("unknown");
                throw new BadRequestException("존재하지 않는 회원 ID가 포함되어 있습니다: " + missingId);
            }
        }

        List<MemberCoupon> memberCoupons = members.stream()
                .filter(member -> !memberCouponRepository.existsByMemberIdAndCouponCouponId(member.getMemberId(), coupon.getCouponId()))
                .map(member -> MemberCoupon.createMemberCoupon(member, coupon))
                .toList();

        if (!memberCoupons.isEmpty()) {
            memberCouponRepository.saveAll(memberCoupons);
        }
    }
}
