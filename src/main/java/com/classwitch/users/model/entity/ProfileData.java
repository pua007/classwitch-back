package com.classwitch.users.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ProfileData {

    @Column(name = "profile_imgUrl")
    private String profileImgUrl; // 프로필 이미지 URL

    @Column(name = "nickname")
    private String nickname; // 닉네임

    @Column(name = "birthday")
    private String birthday; // 생년월일

    // 소셜 미디어 데이터 추가 예정 (로고이미지, 미디어 이름, URL) -> 이것을 배열 형태로 받아야함.

}
