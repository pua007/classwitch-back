package com.classwitch.users.service;

import com.classwitch.users.model.entity.CustomOAuth2User;
import com.classwitch.users.model.entity.UserEntity;
import com.classwitch.users.repository.UserEntityRepository;
import io.jsonwebtoken.security.Password;
import java.util.Map;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomOAuthuserService extends DefaultOAuth2UserService {

    private final UserEntityRepository userEntityRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String oAuthClientName= userRequest.getClientRegistration().getClientName();

        UserEntity userEntity = null;
        String email = "";
        String phoneNumber;

        if(oAuthClientName.equals("naver")) {
            //oAuth2User.getAttributes()은 Map<String, Object>타입의 값을 반환한다.
            Map<String, String> responseMap = (Map<String, String>) (oAuth2User.getAttributes().get("response"));
            email = responseMap.get("email");
            phoneNumber = responseMap.get("mobile");
            if (!userEntityRepository.existsByEmail(email)) {
                String password = "naver_" + oAuth2User.getAttributes().get("id");
                userEntity = UserEntity.of(phoneNumber, email, password, oAuthClientName, true);
                userEntityRepository.save(userEntity);
            }

        }
        return new CustomOAuth2User(email);
    }
}
