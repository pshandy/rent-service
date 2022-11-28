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
    public String showAdditionalContract(Model model) {
        model.addAttribute("additionalContract", new AdditionalContract());
        model.addAttribute("additionalContracts", additionalContractRepository.findAll());
        return "/admin/additionalContract";
    }

    @PostMapping("/admin/additionalContract")
    public ModelAndView saveAdditionalContract(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("additionalContract") @Valid final AdditionalContract additionalContract,
            BindingResult result
    ) {
        try {
            additionalContractRepository.save(additionalContract);
        } catch (RuntimeException ex) {
            ModelAndView mav = new ModelAndView("/admin/additionalContract");
            mav.addObject("message", "Не удалось добавить запись");
            mav.addObject("additionalContract", additionalContract);
            mav.addObject("additionalContracts", additionalContractRepository.findAll());
            return mav;
        }
        redirectAttributes.addFlashAttribute("smessage", "Добавлено");
        return new ModelAndView("redirect:/admin/additionalContract");
    }

    @DeleteMapping(path = "/admin/additionalContract/{id}")
    public ModelAndView deleteAdditionalContract(RedirectAttributes redirectAttributes,
                                             @PathVariable("id") Integer id) {
        try {
            additionalContractRepository.delete(additionalContractRepository.findById(id).get());
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("message", "Не удалось удалить запись");
            return new ModelAndView("redirect:/admin/additionalContract");
        }
        redirectAttributes.addFlashAttribute("smessage", "Удалено");
        return new ModelAndView("redirect:/admin/additionalContract");
    }

    @GetMapping("/admin/additionalContract/{id}")
    public ModelAndView showEditAdditionalContract(RedirectAttributes redirectAttributes,
                                        @PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("/admin/additionalContract");
        mav.addObject("edit", additionalContractRepository.findById(id).get());
        mav.addObject("additionalContract", new AdditionalContract());
        mav.addObject("additionalContracts", additionalContractRepository.findAll());
        return mav;
    }

    @PatchMapping("/admin/additionalContract")
    public ModelAndView updateAdditionalContractForm(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("edit") @Valid final AdditionalContract additionalContract,
            BindingResult result
    ) {
        try {
            additionalContractRepository.save(additionalContract);
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("message", "Не удалось обновить запись");
            return new ModelAndView("redirect:/admin/additionalContract");
        }
        redirectAttributes.addFlashAttribute("smessage", "Обновлено");
        return new ModelAndView("redirect:/admin/additionalContract");
    }



}
