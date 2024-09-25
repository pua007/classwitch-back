package com.classwitch.users;

import com.classwitch.users.controller.request.UserJoinRequest;
import com.classwitch.users.model.dto.UserDTO;
import com.classwitch.users.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // 전체 애플리케이션 컨텍스트를 로드하여 통합 테스트를 지원
@AutoConfigureMockMvc // MockMvc를 자동으로 구성하여 웹 계층의 컨트롤러를 테스트
// 컨트롤러 테스트: 컨트롤러를 실제로 실행하지 않고도 HTTP 요청과 응답을 테스트할 수 있다.
// MockMvc: MockMvc 인스턴스를 주입받아, HTTP 요청을 보내고 응답을 검증할 수 있다.
// 경량 테스트: 실제 서버를 실행하지 않고, 메모리 내에서 테스트를 수행할 수 있다.
class UserControllerTest {

    // @Autowired 애너테이션은 Spring Framework에서 의존성 주입(Dependency Injection)을 수행하기 위해 사용하는 애너테이션.
    // 이 애너테이션을 사용하면 Spring이 해당 필드, 생성자, 메서드에 필요한 의존성을 자동으로 주입해준다.
    @Autowired
    private MockMvc mockMvc;

    // ObjectMapper는 Jackson 라이브러리의 클래스로, Java 객체를 JSON으로 직렬화하거나 JSON을 Java 객체로 역직렬화하는 데 사용한다.
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void 회원가입() throws Exception {
        String phoneNumber = "123456789";
        String email = "test@test.com";
        String password = "password";
        Boolean privacyPolicyAccepted = false;

        // TODO: mocking
        when(userService.join(phoneNumber, email, password, privacyPolicyAccepted)).thenReturn(mock(UserDTO.class));

        mockMvc.perform(post("/api/v1/users/join")
                .contentType(MediaType.APPLICATION_JSON)
                // TODO: add request body
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(phoneNumber, email, password, privacyPolicyAccepted)))
        ).andDo(print()).andExpect(status().isOk());
    }

}