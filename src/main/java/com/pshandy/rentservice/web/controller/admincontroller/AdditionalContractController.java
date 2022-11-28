package com.pshandy.rentservice.web.controller.admincontroller;

import com.pshandy.rentservice.persistence.model.AdditionalContract;
import com.pshandy.rentservice.persistence.repository.AdditionalContractRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdditionalContractController {


    private final AdditionalContractRepository additionalContractRepository;

    public AdditionalContractController(@Autowired AdditionalContractRepository additionalContractRepository) {
        this.additionalContractRepository = additionalContractRepository;
    }

    @GetMapping("/admin/additionalContract")
    public String showProfile(Model model) {
        model.addAttribute("additionalContract", new AdditionalContract());
        model.addAttribute("additionalContracts", additionalContractRepository.findAll());
        return "/tables/additionalContract";
    }

    @PostMapping("/admin/additionalContract")
    public ModelAndView saveWishForm(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("additionalContract") @Valid final AdditionalContract additionalContract,
            BindingResult result
    ) {
        try {
            additionalContractRepository.save(additionalContract);
        } catch (RuntimeException ex) {
            ModelAndView mav = new ModelAndView("/tables/additionalContract");
            mav.addObject("message", "Не удалось добавить запись");
            mav.addObject("wishes", additionalContractRepository.findAll());
            return mav;
        }
        redirectAttributes.addFlashAttribute("smessage", "Добавлено");
        return new ModelAndView("redirect:/admin/additionalContract");
    }

    @DeleteMapping(path = "/admin/additionalContract/{id}")
    public ModelAndView deleteCourseCategory(RedirectAttributes redirectAttributes,
                                             @PathVariable("id") Integer id) {
        try {
            additionalContractRepository.delete(additionalContractRepository.findById(id).get());
        } catch (RuntimeException ex) {
            ModelAndView mav = new ModelAndView("wishEdit");
            mav.addObject("message", "Внутренняя ошибка сервера");
            return mav;
        }
        redirectAttributes.addFlashAttribute("smessage", "Удалено");
        return new ModelAndView("redirect:/admin/additionalContract");
    }


}
