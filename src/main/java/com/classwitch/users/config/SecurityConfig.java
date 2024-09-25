package com.classwitch.users.config;

import static org.springframework.security.config.Customizer.withDefaults;

import com.classwitch.users.handler.OAuth2SuccessHandler;
import com.classwitch.users.service.CustomOAuthuserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration //Spring의 설정 클래스임을 나타냄
@EnableWebSecurity//Spring Security를 활성화 함 애플리케이션의 보안을 구성하는데 필요
@RequiredArgsConstructor//final필드에 대한 생성자를 자동으로 생성해줌 oAuth2UserService의 필드에 대한 생성자를 생성해 준다.
public class SecurityConfig {

    private final DefaultOAuth2UserService oAuth2UserService;
    //private final DefaultOAuth2UserService oAuth2UserService; //DefaultOAuth2UserService는 OAuth2인증과정애서 사용자 정보를 로드하는 서비스임
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean // BCryptPasswordEncoder는 비밀번호를 암호화하는 Spring Security의 암호와기 입니다. 비밀번호를 안전하게 저장하고 검증하는데 필요
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean //이 메서드는 스프링 시큐리티의 필터체인을 구성합 HttpSecurity객체를 사용하여 보안을 구성
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)//csrf => Cross-Site-Request-Forgery이고 보호를 비활성화 함
                //일반적으로 REST API에서는 비활성화 하는것이 일반적임
                //authorizeHttpRequest는 요청에 대한 권한 부여 규칙을 설정함
                .authorizeHttpRequests(auth -> auth
                        //요청경로를 정의 하고 측정 경로에대해 인증없이 접근할 수 있도록 허용함
                        //"/api/*/users/join", "/api/*/users/login","/oauth2/**"이 경로들에 대해서는 인증 없이 접근 가능함
                        .requestMatchers("/api/*/users/join", "/api/*/users/login","/oauth2/**","/api/v1/auth/**", "/api/v1/classes/*", "/api/v1/classes/class-main/*").permitAll()
                        //위의 경로를 제외한 요청은 인증이 필요함
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                                    //기본적으로 스프링 시큐리티는 /oauth2/authorization/{registrationId}를 사용합니다. /oauth2/authorization/naver와 같이 구성됩니다.
                                    //baseUri**는 사용자가 OAuth 2.0 인증을 시작할 때 접근하는 엔드포인트의 기본 경로를 설정하는 메서드입니다.
                                    //URI를 /api/v1/auth/oauth2/{registrationId}로 변경하고 있습니다.
                                .authorizationEndpoint(endpoint -> endpoint.baseUri("/api/v1/auth/oauth2"))
                                    //
                                .redirectionEndpoint(endpoint -> endpoint.baseUri("/oauth2/callback/*"))
                                .userInfoEndpoint(endpoint -> endpoint.userService(oAuth2UserService))
                               .successHandler(oAuth2SuccessHandler)
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
               .cors(withDefaults()
               );
        return http.build();
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source =new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);

        return source;
    }

}
