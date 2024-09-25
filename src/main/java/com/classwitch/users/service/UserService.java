package com.classwitch.users.service;

import com.classwitch.exception.ApplicationException;
import com.classwitch.exception.ErrorCode;
import com.classwitch.users.model.dto.UserDTO;
import com.classwitch.users.model.entity.UserEntity;
import com.classwitch.users.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// Repository: 데이터베이스와 직접 상호작용하여 데이터를 CRUD하는 역할
// Service: 비즈니스 로직을 처리하고, Repository와 Controller 간의 중간 역할을 수행
// Controller: 클라이언트의 요청을 받아 Service를 호출하고, 응답을 클라이언트에 반환

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입
    public UserDTO join(String phoneNumber, String email, String password, Boolean privacyPolicyAccepted) {
        // 회원가입하려는 email으로 회원가입이 된 user가 있는지 체크
        userEntityRepository.findByEmail(email).ifPresent(it -> {
            throw new ApplicationException(ErrorCode.DUPLICATED_USER_EMAIL, String.format("%s is duplicated", email));

        });
        // 회원가입 진행 후 user 데이터를 DB(Database)에 저장
        UserEntity userEntity = userEntityRepository.save(UserEntity.of(phoneNumber, email, bCryptPasswordEncoder.encode(password), privacyPolicyAccepted));
        return UserDTO.fromEntity(userEntity);
    }

    // 로그인
    public void login() {
    }
}
