package com.classwitch.users.controller.response;

import com.classwitch.users.model.dto.UserDTO;
import com.classwitch.users.model.entity.Coupon;
import com.classwitch.users.model.entity.ProfileData;
import com.classwitch.users.model.enums.UserRole;
import com.classwitch.users.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserJoinResponse {

    private Long user_id; // 고유한 유저의 id, key
    private String phoneNumber; // 휴대폰 번호
    private String email; // 이메일
    private Boolean privacyPolicyAccepted;

    private ProfileData profileData; // 프로필 데이터
    private List<Integer> myClasses; // 내가 학습 중인 클래스, 내가 구매한 클래스
    private List<Integer> favoriteClasses; // 내가 찜한 클래스
    private List<Integer> myCollabs; // 내가 참여하고 있는 콜레보레이션
    private List<Integer> shoppingCart; // 장바구니 리스트
    private List<Coupon> coupons; // 쿠폰

    private Boolean magazineSubscriptionStatus; // 매거진 구독여부
    private UserRole userRole; // 유저 타입, 유저 권한
    private UserStatus status; // 유저의 상태를 나타내는 열거형 필드

    public static UserJoinResponse fromUser(UserDTO user) {
        return new UserJoinResponse(
                user.getUserId(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getPrivacyPolicyAccepted(),
                user.getProfileData(),
                user.getMyClasses(),
                user.getFavoriteClasses(),
                user.getMyCollabs(),
                user.getShoppingCart(),
                user.getCoupons(),
                user.getMagazineSubscriptionStatus(),
                user.getUserRole(),
                user.getStatus()
        );
    }

}
