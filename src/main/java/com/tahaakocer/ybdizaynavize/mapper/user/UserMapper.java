package com.tahaakocer.ybdizaynavize.mapper.user;

import com.tahaakocer.ybdizaynavize.dto.user.UserDto;
import com.tahaakocer.ybdizaynavize.dto.user.request.LoginRequest;
import com.tahaakocer.ybdizaynavize.dto.user.request.RegisterRequest;
import com.tahaakocer.ybdizaynavize.dto.user.response.RegisterResponse;
import com.tahaakocer.ybdizaynavize.model.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDtoToUser(UserDto userDTO);

    UserDto userToUserDto(User savedUser);

    UserDto registerRequestToUserDto(RegisterRequest registerRequest);

    RegisterResponse userDtoToRegisterResponse(UserDto userDTO);

    UserDto loginRequestToUserDto(LoginRequest loginRequest);
}
