package com.pshandy.rentservice.web.dto;

import com.pshandy.rentservice.validation.EmailValid;
import com.pshandy.rentservice.validation.PasswordMatches;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@PasswordMatches
public class UserDto {

    @NotBlank
    @NotNull
    private String firstName;

    @NotBlank
    @NotNull
    private String lastName;

    private String middleName;

    @NotBlank
    @NotNull
    private String password;

    @NotBlank
    private String matchingPassword;

    @NotBlank
    @NotNull
    @EmailValid
    private String email;

}
