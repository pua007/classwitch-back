package com.classwitch.users.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// @AllArgsConstructor 애너테이션은 Java의 Lombok 라이브러리에서 제공하는 기능으로
// 클래스의 모든 필드를 파라미터로 받는 생성자를 자동으로 생성해준다.
// 이 애너테이션을 사용하면, 수동으로 생성자를 작성하지 않고도 클래스의 모든 필드를 초기화할 수 있는 생성자를 쉽게 생성할 수 있다.
public class UserJoinRequest {

    private String phoneNumber; // 휴대폰 번호
    private String email; // 이메일
    private String password; // 비밀번호
    private Boolean privacyPolicyAccepted; // 필수약관체크 여부

}
