package com.pshandy.rentservice.web.controller.usercontroller;

import com.pshandy.rentservice.persistence.model.Wish;
import com.pshandy.rentservice.persistence.repository.WishRepository;
import com.pshandy.rentservice.security.AppUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserWishController {

    private final WishRepository wishRepository;

    public UserWishController(@Autowired WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    @GetMapping("/user/wish")
    public String showWishForm(Model model) {
        model.addAttribute("wish", new Wish());
        return "/user/wish";
    }

    @PostMapping("/user/wish")
    public ModelAndView saveWishForm(
            @ModelAttribute("wish") final Wish wish,
            BindingResult result
    ) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            wish.setUser(((AppUserDetails)principal).getUser());
            wishRepository.save(wish);
        } catch (RuntimeException ex) {
            ModelAndView mav = new ModelAndView("/user/wish", "wish", wish);
            mav.addObject("message", "Не удалось отправить");
            return mav;
        }
        return new ModelAndView("/user/wish", "smessage", "Отправлено");
    }

}
