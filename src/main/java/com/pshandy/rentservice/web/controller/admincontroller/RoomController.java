package com.pshandy.rentservice.web.controller.admincontroller;

import com.pshandy.rentservice.persistence.model.Room;
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
    public String showRoom(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("rooms", roomRepository.findAll());
        return "/admin/room";
    }

    @PostMapping("/admin/room")
    public ModelAndView saveRoom(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("room") @Valid final Room room,
            BindingResult result
    ) {
        try {
            roomRepository.save(room);
        } catch (RuntimeException ex) {
            ModelAndView mav = new ModelAndView("/admin/room");
            mav.addObject("message", "Не удалось добавить запись");
            mav.addObject("room", room);
            mav.addObject("rooms", roomRepository.findAll());
            return mav;
        }
        redirectAttributes.addFlashAttribute("smessage", "Добавлено");
        return new ModelAndView("redirect:/admin/room");
    }

    @DeleteMapping(path = "/admin/room/{id}")
    public ModelAndView deleteRoom(RedirectAttributes redirectAttributes,
                                             @PathVariable("id") Integer id) {
        try {
            roomRepository.delete(roomRepository.findById(id).get());
        } catch (RuntimeException ex) {
            ModelAndView mav = new ModelAndView("/admin/room");
            redirectAttributes.addFlashAttribute("message", "Не удалось удалить запись");
            return new ModelAndView("redirect:/admin/room");
        }
        redirectAttributes.addFlashAttribute("smessage", "Удалено");
        return new ModelAndView("redirect:/admin/room");
    }

    @GetMapping("/admin/room/{id}")
    public ModelAndView showEditRoom(RedirectAttributes redirectAttributes,
                                         @PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("/admin/room");
        mav.addObject("edit", roomRepository.findById(id).get());
        mav.addObject("room", new Room());
        mav.addObject("rooms", roomRepository.findAll());
        return mav;
    }

    @PatchMapping("/admin/room")
    public ModelAndView updateRoomForm(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("edit") @Valid final Room room,
            BindingResult result
    ) {
        try {
            roomRepository.save(room);
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("message", "Не удалось обновить запись");
            return new ModelAndView("redirect:/admin/room");
        }
        redirectAttributes.addFlashAttribute("smessage", "Обновлено");
        return new ModelAndView("redirect:/admin/room");
    }
    

}
