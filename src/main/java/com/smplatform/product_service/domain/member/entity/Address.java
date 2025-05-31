package com.smplatform.product_service.domain.member.entity;

import com.smplatform.product_service.domain.member.dto.AddressRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    private String alias;
    private String zipcode;
    @Column(name = "default_address")
    private String defaultAddress;
    @Column(name = "detail_address")
    private String detailAddress;
    @Column(name = "receiver_name")
    private String receiverName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "is_default")
    private Boolean isDefault;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.createdAt = localDateTime;
        this.updatedAt = localDateTime;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void update(AddressRequestDto.AddressUpdate addressUpdate) {
        Optional.ofNullable(addressUpdate.getAlias()).ifPresent(alias -> this.alias = alias);
        Optional.ofNullable(addressUpdate.getZipcode()).ifPresent(zipcode -> this.zipcode = zipcode);
        Optional.ofNullable(addressUpdate.getAddress1()).ifPresent(address1 -> this.defaultAddress = address1);
        Optional.ofNullable(addressUpdate.getAddress2()).ifPresent(address2 -> this.detailAddress = address2);
        Optional.ofNullable(addressUpdate.getReceiverName()).ifPresent(receiverName -> this.receiverName = receiverName);
        Optional.ofNullable(addressUpdate.getPhoneNumber()).ifPresent(phoneNumber -> this.phoneNumber = phoneNumber);
        Optional.ofNullable(addressUpdate.getIsDefault()).ifPresent(isDefault -> this.isDefault = isDefault == 1);
    }

}
