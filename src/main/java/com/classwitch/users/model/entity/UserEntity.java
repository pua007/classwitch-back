package com.classwitch.users.model.entity;

import com.classwitch.users.model.enums.UserRole;
import com.classwitch.users.model.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Table(name = "\"users\"")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "privacy_policy_accepted", nullable = false)
    private Boolean privacyPolicyAccepted = false; // 기본 값을 false로 설정

    @Embedded
    private ProfileData profileData;

    @ElementCollection
    @CollectionTable(name = "user_classes", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "my_classes")
    private List<Integer> myClasses;

    @ElementCollection
    @CollectionTable(name = "user_favorite_classes", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "favorite_classes")
    private List<Integer> favoriteClasses;

    @ElementCollection
    @CollectionTable(name = "user_shopping_cart", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "shopping_cart")
    private List<Integer> shoppingCart;

    @ElementCollection
    @CollectionTable(name = "user_coupons", joinColumns = @JoinColumn(name = "user_id"))
    private List<Coupon> coupons;

    @ElementCollection
    @CollectionTable(name = "user_collabs", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "my_collabs")
    private List<Integer> myCollabs;

    @Column(name = "magazine_subscription_status")
    private Boolean magazineSubscriptionStatus;

    @Column(name = "user_role")
    private UserRole userRole;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private UserStatus userStatus; // 유저의 상태를 나타내는 열거형 필드

    @Column(name = "user_type")
    private String userType;

    public static UserEntity of(String phoneNumber, String email, String password, String userType ,Boolean privacyPolicyAccepted) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPhoneNumber(phoneNumber);
        userEntity.setEmail(email);
        userEntity.setUserType(userType);
        userEntity.setPassword(password);
        userEntity.setPrivacyPolicyAccepted(privacyPolicyAccepted);
        return userEntity;
    }

    public static UserEntity of(String phoneNumber, String email, String password, Boolean privacyPolicyAccepted) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPhoneNumber(phoneNumber);
        userEntity.setEmail(email);
        userEntity.setUserType("app");
        userEntity.setPassword(password);
        userEntity.setPrivacyPolicyAccepted(privacyPolicyAccepted);
        return userEntity;
    }

    @PrePersist
    void createdAt() {
        this.createdAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }

}
