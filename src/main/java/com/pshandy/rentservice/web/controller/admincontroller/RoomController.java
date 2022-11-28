package com.pshandy.rentservice.web.controller.admincontroller;

import com.pshandy.rentservice.persistence.model.Room;
import com.pshandy.rentservice.persistence.model.Wish;
import com.pshandy.rentservice.persistence.repository.RoomRepository;
import com.pshandy.rentservice.security.AppUserDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RoomController {

    private final RoomRepository roomRepository;

    public RoomController(@Autowired RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/admin/room")
    public String showProfile(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("rooms", roomRepository.findAll());
        return "/tables/room";
    }

    @PostMapping("/admin/room")
    public ModelAndView saveWishForm(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("room") @Valid final Room room,
            BindingResult result
    ) {
        try {
            roomRepository.save(room);
        } catch (RuntimeException ex) {
            ModelAndView mav = new ModelAndView("/tables/room");
            mav.addObject("message", "Не удалось добавить запись");
            mav.addObject("wishes", roomRepository.findAll());
            return mav;
        }
        redirectAttributes.addFlashAttribute("smessage", "Добавлено");
        return new ModelAndView("redirect:/admin/room");
    }

    @DeleteMapping(path = "/admin/room/{id}")
    public ModelAndView deleteCourseCategory(RedirectAttributes redirectAttributes,
                                             @PathVariable("id") Integer id) {
        try {
            roomRepository.delete(roomRepository.findById(id).get());
        } catch (RuntimeException ex) {
            ModelAndView mav = new ModelAndView("wishEdit");
            mav.addObject("message", "Внутренняя ошибка сервера");
            return mav;
        }
        redirectAttributes.addFlashAttribute("smessage", "Удалено");
        return new ModelAndView("redirect:/admin/room");
    }

}
