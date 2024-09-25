package com.classwitch.users.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class Coupon {

    @Column(name = "coupon_code", nullable = false, length = 50)
    private String couponCode; // 쿠폰 코드

    @Column(name = "description", length = 255)
    private String description; // 쿠폰 설명

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate; // 만료일시

    // equals() 및 hashCode() 메서드 구현
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coupon coupon)) return false;
        return Objects.equals(couponCode, coupon.couponCode) &&
                Objects.equals(description, coupon.description) &&
                Objects.equals(expirationDate, coupon.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(couponCode, description, expirationDate);
    }
}
