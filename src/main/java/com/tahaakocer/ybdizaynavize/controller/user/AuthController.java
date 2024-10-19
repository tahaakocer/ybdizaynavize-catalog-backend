package com.tahaakocer.ybdizaynavize.controller.user;

import com.tahaakocer.ybdizaynavize.dto.ApiResponse;
import com.tahaakocer.ybdizaynavize.dto.user.UserDto;
import com.tahaakocer.ybdizaynavize.dto.user.request.LoginRequest;
import com.tahaakocer.ybdizaynavize.dto.user.request.RegisterRequest;
import com.tahaakocer.ybdizaynavize.dto.user.response.LoginResponse;
import com.tahaakocer.ybdizaynavize.dto.user.response.RegisterResponse;
import com.tahaakocer.ybdizaynavize.mapper.user.UserMapper;
import com.tahaakocer.ybdizaynavize.service.user.impl.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;
    private final UserMapper userMapper;

    public AuthController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid RegisterRequest registerRequest){
        UserDto userDTO = this.userService.register(this.userMapper.registerRequestToUserDto(registerRequest));
        RegisterResponse response = this.userMapper.userDtoToRegisterResponse(userDTO);
        return ResponseEntity.ok(ApiResponse.builder()
                .status(201)
                .message("registered successfully")
                .data(response)
                .build());

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid LoginRequest loginRequest){
        UserDto userDTO = this.userMapper.loginRequestToUserDto(loginRequest);
        LoginResponse loginResponse = this.userService.login(userDTO);
        return ResponseEntity.ok(loginResponse);
    }
    @GetMapping("/validate/{token}")
    public ResponseEntity<String> validate(@PathVariable("token")String token) throws Exception {

        boolean isValidate= this.userService.validate(token);

        return ResponseEntity.ok(isValidate ?"Is valid":"Not valid token");
    }
    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.userService.refreshToken(request, response);
    }

}

