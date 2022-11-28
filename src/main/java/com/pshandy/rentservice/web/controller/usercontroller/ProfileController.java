package com.pshandy.rentservice.web.controller.usercontroller;

import com.pshandy.rentservice.persistence.model.User;
import com.pshandy.rentservice.security.AppUserDetails;
import com.pshandy.rentservice.service.UserService;
import com.pshandy.rentservice.web.dto.UserDto;
import com.pshandy.rentservice.web.error.UserAlreadyExistException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

    private final UserService userService;

    public ProfileController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/profile")
    public String showProfile(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ((AppUserDetails)principal).getUser();
        model.addAttribute("user", userService.getUserDto(user));
        return "/user/profile";
    }

    @PatchMapping("/user/profile")
    public ModelAndView changeUserAccount(@ModelAttribute("user") @Valid final UserDto userDto,
                                            BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("/user/profile", result.getModel());
        }
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = ((AppUserDetails)principal).getUser();
            User registered = userService.updateUserAccount(user, userDto);
        } catch (UserAlreadyExistException exp) {
            ModelAndView mav = new ModelAndView("/user/profile", "user", userDto);
            mav.addObject("message", "Пользователь с такой почтой уже существует.");
            return mav;
        } catch (RuntimeException ex) {
            ModelAndView mav = new ModelAndView("/user/profile", "user", userDto);
            mav.addObject("message", "Внутренняя ошибка сервера.");
            return mav;
        }
        return new ModelAndView("/user/profile", "smessage", "Данные обновлены.");
    }

}
