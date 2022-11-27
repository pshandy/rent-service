package com.pshandy.rentservice.web.dto;

import com.pshandy.rentservice.validation.EmailValid;
import com.pshandy.rentservice.validation.PasswordMatches;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@PasswordMatches
public class UserDto {

    @NotBlank(message = "Поле *Имя* не должно быть пустым")
    @NotNull
    private String firstName;

    @NotBlank(message = "Поле *Фамилия* не должно быть пустым")
    @NotNull
    private String lastName;

    private String middleName;

    @NotBlank(message = "Поле *Пароль* не должно быть пустым")
    @NotNull
    private String password;

    @NotBlank(message = "Поле *Повторите пароль* не должно быть пустым")
    private String matchingPassword;

    @NotBlank(message = "Поле *Почта* не должно быть пустым")
    @NotNull
    @EmailValid
    private String email;

    @NotBlank(message = "Поле *Номер телефона* не должно быть пустым")
    @NotNull
    private String phoneNumber;

}
