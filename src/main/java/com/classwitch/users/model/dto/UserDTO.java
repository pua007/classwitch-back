package com.classwitch.users.model.dto;

import com.classwitch.users.model.entity.Coupon;
import com.classwitch.users.model.entity.ProfileData;
import com.classwitch.users.model.entity.UserEntity;
import com.classwitch.users.model.enums.UserRole;
import com.classwitch.users.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.annotation.Id;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO {
    private Long userId; // 고유한 유저의 id, key
    private String phoneNumber; // 휴대폰 번호
    private String email; // 이메일
    private String password; // 비밀번호
    private Boolean privacyPolicyAccepted; // 필수약관체크 여부

    private ProfileData profileData; // 프로필 데이터
    private List<Integer> myClasses; // 내가 학습 중인 클래스, 내가 구매한 클래스
    private List<Integer> favoriteClasses; // 내가 찜한 클래스
    private List<Integer> myCollabs; // 내가 참여하고 있는 콜레보레이션
    private List<Integer> shoppingCart; // 장바구니 리스트
    private List<Coupon> coupons; // 쿠폰

    private Boolean magazineSubscriptionStatus; // 매거진 구독여부
    private UserRole userRole; // 유저 타입, 유저 권한
    private Timestamp createdAt; // 계정정보 생성일시
    private Timestamp updatedAt; // 계정정보 수정일시
    private Timestamp deletedAt; // 계정정보 삭제일시
    private UserStatus status; // 유저의 상태를 나타내는 열거형 필드

    public static UserDTO fromEntity(UserEntity entity) {
        return new UserDTO(
                entity.getUserId(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getPrivacyPolicyAccepted(),
                entity.getProfileData(),
                entity.getMyClasses(),
                entity.getFavoriteClasses(),
                entity.getMyCollabs(),
                entity.getShoppingCart(),
                entity.getCoupons(),
                entity.getMagazineSubscriptionStatus(),
                entity.getUserRole(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getDeletedAt(),
                entity.getUserStatus()
        );
    }

}
