package com.smplatform.product_service.domain.member.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "withdraw_members")
public class WithdrawMember {

    @Id
    @NotNull
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "memo")
    private String memo;

    @CreationTimestamp
    @Column(name = "withdraw_at")
    private LocalDateTime withdrawAt;

    public WithdrawMember(Member member, String memo) {
        this.member = member;
        this.memo = memo;
    }
}
