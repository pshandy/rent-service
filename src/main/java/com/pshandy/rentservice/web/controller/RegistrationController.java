package com.pshandy.rentservice.web.controller;

import com.pshandy.rentservice.persistence.model.User;
import com.pshandy.rentservice.service.UserService;
import com.pshandy.rentservice.web.dto.UserDto;
import com.pshandy.rentservice.web.error.UserAlreadyExistException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(
            @Autowired UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping("/user/registration")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/user/registration")
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid final UserDto userDto,
                                            BindingResult result) {

        System.out.println(result);

        try {
            User registered = userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException exp) {
            ModelAndView mav = new ModelAndView("registration", "user", userDto);
            mav.addObject("message", "Пользователь с такой почтой уже существует.");
            return mav;
        } catch (RuntimeException ex) {
            ModelAndView mav = new ModelAndView("registration", "user", userDto);
            mav.addObject("message", "Ошибка.");
            return mav;
        }
        return new ModelAndView("registration");

    }

}
