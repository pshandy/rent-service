package com.pshandy.rentservice.web.controller.admincontroller;

import com.pshandy.rentservice.persistence.model.User;
import com.pshandy.rentservice.persistence.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/admin/user")
    public String showProfile(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userRepository.findAll());
        return "/tables/user";
    }

    @PostMapping("/admin/user")
    public ModelAndView saveWishForm(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("user") @Valid final User user,
            BindingResult result
    ) {
        try {
            userRepository.save(user);
        } catch (RuntimeException ex) {
            ModelAndView mav = new ModelAndView("/tables/user");
            mav.addObject("message", "Не удалось добавить запись");
            mav.addObject("wishes", userRepository.findAll());
            return mav;
        }
        redirectAttributes.addFlashAttribute("smessage", "Добавлено");
        return new ModelAndView("redirect:/admin/user");
    }

    @DeleteMapping(path = "/admin/user/{id}")
    public ModelAndView deleteCourseCategory(RedirectAttributes redirectAttributes,
                                             @PathVariable("id") Integer id) {
        try {
            userRepository.delete(userRepository.findById(id).get());
        } catch (RuntimeException ex) {
            ModelAndView mav = new ModelAndView("wishEdit");
            mav.addObject("message", "Внутренняя ошибка сервера");
            return mav;
        }
        redirectAttributes.addFlashAttribute("smessage", "Удалено");
        return new ModelAndView("redirect:/admin/user");
    }
    
}
