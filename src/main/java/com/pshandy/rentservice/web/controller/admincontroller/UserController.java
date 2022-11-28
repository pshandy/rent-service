package com.pshandy.rentservice.web.controller.admincontroller;

import com.pshandy.rentservice.persistence.model.User;
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
    public String showUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userRepository.findAll());
        return "/admin/user";
    }

    @PostMapping("/admin/user")
    public ModelAndView saveUser(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("user") @Valid final User user,
            BindingResult result
    ) {
        try {
            userRepository.save(user);
        } catch (RuntimeException ex) {
            ModelAndView mav = new ModelAndView("/admin/user");
            mav.addObject("message", "Не удалось добавить запись");
            mav.addObject("user", user);
            mav.addObject("users", userRepository.findAll());
            return mav;
        }
        redirectAttributes.addFlashAttribute("smessage", "Добавлено");
        return new ModelAndView("redirect:/admin/user");
    }

    @DeleteMapping(path = "/admin/user/{id}")
    public ModelAndView deleteUser(RedirectAttributes redirectAttributes,
                                             @PathVariable("id") Integer id) {
        try {
            userRepository.delete(userRepository.findById(id).get());
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("message", "Не удалось удалить запись");
            return new ModelAndView("redirect:/admin/user");
        }
        redirectAttributes.addFlashAttribute("smessage", "Удалено");
        return new ModelAndView("redirect:/admin/user");
    }

    @GetMapping("/admin/user/{id}")
    public ModelAndView showEditUser(RedirectAttributes redirectAttributes,
                                         @PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("/admin/user");
        mav.addObject("edit", userRepository.findById(id).get());
        mav.addObject("user", new User());
        mav.addObject("users", userRepository.findAll());
        return mav;
    }

    @PatchMapping("/admin/user")
    public ModelAndView updateUserForm(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("edit") @Valid final User user,
            BindingResult result
    ) {
        try {
            userRepository.save(user);
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("message", "Не удалось обновить запись");
            return new ModelAndView("redirect:/admin/user");
        }
        redirectAttributes.addFlashAttribute("smessage", "Обновлено");
        return new ModelAndView("redirect:/admin/user");
    }
    
}
