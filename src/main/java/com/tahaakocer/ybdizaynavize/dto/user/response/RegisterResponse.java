package com.tahaakocer.ybdizaynavize.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RegisterResponse {
    private UUID id;
    private String email;
//    private String name;
//    private String phoneNumber;
}
