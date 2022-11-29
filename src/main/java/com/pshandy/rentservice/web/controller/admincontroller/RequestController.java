package com.pshandy.rentservice.web.controller.admincontroller;

import com.pshandy.rentservice.persistence.model.Request;
import com.pshandy.rentservice.persistence.repository.RequestRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RequestController {

    private final RequestRepository requestRepository;

    public RequestController(@Autowired RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @GetMapping("/admin/request")
    public String showRequest(Model model) {
        model.addAttribute("request", new Request());
        model.addAttribute("requests", requestRepository.findAll());
        return "/admin/request";
    }

    @PostMapping("/admin/request")
    public ModelAndView saveRequest(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("request") @Valid final Request request,
            BindingResult result
    ) {
        try {
            requestRepository.save(request);
        } catch (RuntimeException ex) {
            ModelAndView mav = new ModelAndView("/admin/request");
            mav.addObject("message", "Не удалось добавить запись");
            mav.addObject("request", request);
            mav.addObject("requests", requestRepository.findAll());
            return mav;
        }
        redirectAttributes.addFlashAttribute("smessage", "Добавлено");
        return new ModelAndView("redirect:/admin/request");
    }

    @DeleteMapping(path = "/admin/request/{id}")
    public ModelAndView deleteRequest(RedirectAttributes redirectAttributes,
                                   @PathVariable("id") Integer id) {
        try {
            requestRepository.delete(requestRepository.findById(id).get());
        } catch (RuntimeException ex) {
            ModelAndView mav = new ModelAndView("/admin/request");
            redirectAttributes.addFlashAttribute("message", "Не удалось удалить запись");
            return new ModelAndView("redirect:/admin/request");
        }
        redirectAttributes.addFlashAttribute("smessage", "Удалено");
        return new ModelAndView("redirect:/admin/request");
    }

    @GetMapping("/admin/request/{id}")
    public ModelAndView showEditRequest(RedirectAttributes redirectAttributes,
                                     @PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("/admin/request");
        mav.addObject("edit", requestRepository.findById(id).get());
        mav.addObject("request", new Request());
        mav.addObject("requests", requestRepository.findAll());
        return mav;
    }

    @PatchMapping("/admin/request")
    public ModelAndView updateRequestForm(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("edit") @Valid final Request request,
            BindingResult result
    ) {
        try {
            requestRepository.save(request);
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("message", "Не удалось обновить запись");
            return new ModelAndView("redirect:/admin/request");
        }
        redirectAttributes.addFlashAttribute("smessage", "Обновлено");
        return new ModelAndView("redirect:/admin/request");
    }


}
