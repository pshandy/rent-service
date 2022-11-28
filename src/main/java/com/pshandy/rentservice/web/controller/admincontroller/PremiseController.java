package com.pshandy.rentservice.web.controller.admincontroller;

import com.pshandy.rentservice.persistence.model.Premise;
import com.pshandy.rentservice.persistence.repository.PremiseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PremiseController {

    private final PremiseRepository premiseRepository;

    public PremiseController(@Autowired PremiseRepository premiseRepository) {
        this.premiseRepository = premiseRepository;
    }

    @GetMapping("/admin/premise")
    public String showProfile(Model model) {
        model.addAttribute("premise", new Premise());
        model.addAttribute("premises", premiseRepository.findAll());
        return "/tables/premise";
    }

    @PostMapping("/admin/premise")
    public ModelAndView savePremiseForm(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("premise") @Valid final Premise premise,
            BindingResult result
    ) {
        try {
            premiseRepository.save(premise);
        } catch (RuntimeException ex) {
            ModelAndView mav = new ModelAndView("/tables/premise");
            mav.addObject("message", "Не удалось добавить запись");
            mav.addObject("premises", premiseRepository.findAll());
            return mav;
        }
        redirectAttributes.addFlashAttribute("smessage", "Добавлено");
        return new ModelAndView("redirect:/admin/premise");
    }

    @DeleteMapping(path = "/admin/premise/{id}")
    public ModelAndView deleteCourseCategory(RedirectAttributes redirectAttributes,
                                             @PathVariable("id") Integer id) {
        try {
            premiseRepository.delete(premiseRepository.findById(id).get());
        } catch (RuntimeException ex) {
            ModelAndView mav = new ModelAndView("/tables/premise");
            mav.addObject("message", "Не удалось добавить запись");
            mav.addObject("premises", premiseRepository.findAll());
            return mav;
        }
        redirectAttributes.addFlashAttribute("smessage", "Удалено");
        return new ModelAndView("redirect:/admin/premise");
    }


}
