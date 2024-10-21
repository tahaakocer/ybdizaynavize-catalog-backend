package com.tahaakocer.ybdizaynavize.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Email is required")
    private String email;

//    @NotBlank(message = "Phone number is required")
//    private String phoneNumber;

    @NotBlank(message = "Password is required")
    private String password;

}
