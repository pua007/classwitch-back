package com.classwitch.users.controller;

import com.classwitch.users.controller.request.UserJoinRequest;
import com.classwitch.users.controller.response.Response;
import com.classwitch.users.controller.response.UserJoinResponse;
import com.classwitch.users.model.dto.UserDTO;
import com.classwitch.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request) {
        UserDTO user = userService.join(request.getPhoneNumber(), request.getEmail(), request.getPassword(), request.getPrivacyPolicyAccepted());
        return Response.success(UserJoinResponse.fromUser(user));
    }

}
