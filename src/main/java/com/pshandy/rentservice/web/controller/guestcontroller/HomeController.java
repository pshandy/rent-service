package com.pshandy.rentservice.web.controller.guestcontroller;

import com.pshandy.rentservice.persistence.model.Premise;
import com.pshandy.rentservice.persistence.repository.PremiseRepository;
import com.pshandy.rentservice.web.dto.SearchDto;
import com.pshandy.rentservice.web.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final PremiseRepository premiseRepository;

    public HomeController(@Autowired PremiseRepository premiseRepository) {
        this.premiseRepository = premiseRepository;
    }


    @GetMapping("/homepage")
    public String home(Model model) {
        model.addAttribute("searchDto", new SearchDto());
        return "homepage";
    }

    @PostMapping("/homepage")
    public ModelAndView search(@ModelAttribute("searchDto") @Valid final SearchDto searchDto) {
        ModelAndView mav = new ModelAndView("/filter");
        mav.addObject("premises", premiseRepository.findAll());
        return mav;
    }

}
