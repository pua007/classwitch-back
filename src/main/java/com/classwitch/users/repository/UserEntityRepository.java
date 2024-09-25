package com.classwitch.users.repository;

import com.classwitch.users.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    // Optional<UserEntity>: 결과를 Optional로 감싸서 반환합니다.
    // Optional은 결과가 존재하지 않을 수 있는 경우 (즉, 사용자가 존재하지 않는 경우)
    // null을 반환하는 대신 빈 값을 안전하게 처리할 수 있게 해준다.
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);

}
