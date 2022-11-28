package com.pshandy.rentservice.web.controller.admincontroller;

import com.pshandy.rentservice.persistence.model.Wish;
import com.pshandy.rentservice.persistence.repository.WishRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WishController {

    private final WishRepository wishRepository;

    public WishController(@Autowired WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    @GetMapping("/admin/wish")
    public String showProfile(Model model) {
        model.addAttribute("wish", new Wish());
        model.addAttribute("wishes", wishRepository.findAll());
        return "/tables/wish";
    }

    @PostMapping("/admin/wish")
    public ModelAndView saveWishForm(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("wish") @Valid final Wish wish,
            BindingResult result
    ) {
        try {
            wishRepository.save(wish);
        } catch (RuntimeException ex) {
            ModelAndView mav = new ModelAndView("/tables/wish");
            mav.addObject("message", "Не удалось добавить запись");
            mav.addObject("wishes", wishRepository.findAll());
            return mav;
        }
        redirectAttributes.addFlashAttribute("smessage", "Добавлено");
        return new ModelAndView("redirect:/admin/wish");
    }

    @DeleteMapping(path = "/admin/wish/{id}")
    public ModelAndView deleteCourseCategory(RedirectAttributes redirectAttributes,
                                             @PathVariable("id") Integer id) {
        try {
            wishRepository.delete(wishRepository.findById(id).get());
        } catch (RuntimeException ex) {
            ModelAndView mav = new ModelAndView("/tables/wish");
            mav.addObject("message", "Не удалось добавить запись");
            mav.addObject("wishes", wishRepository.findAll());
            return mav;
        }
        redirectAttributes.addFlashAttribute("smessage", "Удалено");
        return new ModelAndView("redirect:/admin/wish");
    }

}
