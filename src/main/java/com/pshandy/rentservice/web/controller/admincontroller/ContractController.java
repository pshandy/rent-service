package com.pshandy.rentservice.web.controller.admincontroller;

import com.pshandy.rentservice.persistence.model.Contract;
import com.pshandy.rentservice.persistence.repository.ContractRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContractController {


    private final ContractRepository contractRepository;

    public ContractController(@Autowired ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @GetMapping("/admin/contract")
    public String showContract(Model model) {
        model.addAttribute("contract", new Contract());
        model.addAttribute("contracts", contractRepository.findAll());
        return "/admin/contract";
    }

    @PostMapping("/admin/contract")
    public ModelAndView saveContractForm(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("contract") @Valid final Contract contract,
            BindingResult result
    ) {
        try {
            contractRepository.save(contract);
        } catch (RuntimeException ex) {
            ModelAndView mav = new ModelAndView("/admin/contract");
            mav.addObject("message", "Не удалось добавить запись");
            mav.addObject("contract", contract);
            mav.addObject("contracts", contractRepository.findAll());
            return mav;
        }
        redirectAttributes.addFlashAttribute("smessage", "Добавлено");
        return new ModelAndView("redirect:/admin/contract");
    }

    @DeleteMapping(path = "/admin/contract/{id}")
    public ModelAndView deleteContract(RedirectAttributes redirectAttributes,
                                             @PathVariable("id") Integer id) {
        try {
            contractRepository.delete(contractRepository.findById(id).get());
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("message", "Не удалось удалить запись");
            return new ModelAndView("redirect:/admin/contract");
        }
        redirectAttributes.addFlashAttribute("smessage", "Удалено");
        return new ModelAndView("redirect:/admin/contract");
    }

    @GetMapping("/admin/contract/{id}")
    public ModelAndView showEditContract(RedirectAttributes redirectAttributes,
                                         @PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("/admin/contract");
        mav.addObject("edit", contractRepository.findById(id).get());
        mav.addObject("contract", new Contract());
        mav.addObject("contracts", contractRepository.findAll());
        return mav;
    }

    @PatchMapping("/admin/contract")
    public ModelAndView updateContractForm(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("edit") @Valid final Contract contract,
            BindingResult result
    ) {
        try {
            contractRepository.save(contract);
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("message", "Не удалось обновить запись");
            return new ModelAndView("redirect:/admin/contract");
        }
        redirectAttributes.addFlashAttribute("smessage", "Обновлено");
        return new ModelAndView("redirect:/admin/contract");
    }


}
