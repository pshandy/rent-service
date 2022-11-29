package com.pshandy.rentservice.web.controller.usercontroller;

import com.pshandy.rentservice.persistence.model.Request;
import com.pshandy.rentservice.persistence.model.Room;
import com.pshandy.rentservice.persistence.model.Wish;
import com.pshandy.rentservice.persistence.repository.PremiseRepository;
import com.pshandy.rentservice.persistence.repository.RequestRepository;
import com.pshandy.rentservice.persistence.repository.RoomRepository;
import com.pshandy.rentservice.security.AppUserDetails;
import com.pshandy.rentservice.web.dto.SearchDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class UserRequestController {

    private final RequestRepository requestRepository;

    private final PremiseRepository premiseRepository;

    public UserRequestController(@Autowired RequestRepository requestRepository,
                                 @Autowired PremiseRepository premiseRepository) {
        this.requestRepository = requestRepository;
        this.premiseRepository = premiseRepository;
    }

    @PostMapping("/user/request")
    public ModelAndView search(@RequestParam Integer room,
                               RedirectAttributes redirectAttributes) {
        Request request = new Request();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setPremise(premiseRepository.findById(room).get());
        request.setUser(((AppUserDetails)principal).getUser());
        try {
            requestRepository.save(request);
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("message", "Не удалось отправить заявку");
            return new ModelAndView("redirect:/homepage");
        }
        redirectAttributes.addFlashAttribute("smessage", "Отправлено");
        return new ModelAndView("redirect:/homepage");
    }

}
