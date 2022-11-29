package com.pshandy.rentservice.web.controller.admincontroller;

import com.pshandy.rentservice.persistence.model.Wish;
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
        return "/admin/wish";
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
            ModelAndView mav = new ModelAndView("/admin/wish");
            mav.addObject("message", "Не удалось добавить запись");
            mav.addObject("wish", wish);
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
            redirectAttributes.addFlashAttribute("message", "Не удалось удалить запись");
            return new ModelAndView("redirect:/admin/wish");
        }
        redirectAttributes.addFlashAttribute("smessage", "Удалено");
        return new ModelAndView("redirect:/admin/wish");
    }

    @GetMapping("/admin/wish/{id}")
    public ModelAndView showEditWish(RedirectAttributes redirectAttributes,
                                         @PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("/admin/wish");
        mav.addObject("edit", wishRepository.findById(id).get());
        mav.addObject("wish", new Wish());
        mav.addObject("wishes", wishRepository.findAll());
        return mav;
    }

    @PatchMapping("/admin/wish")
    public ModelAndView updateWishForm(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("edit") @Valid final Wish wish,
            BindingResult result
    ) {
        try {
            wishRepository.save(wish);
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("message", "Не удалось обновить запись");
            return new ModelAndView("redirect:/admin/wish");
        }
        redirectAttributes.addFlashAttribute("smessage", "Обновлено");
        return new ModelAndView("redirect:/admin/wish");
    }
    
}
