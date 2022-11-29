package com.pshandy.rentservice.web.controller.guestcontroller;

import com.pshandy.rentservice.persistence.model.Contract;
import com.pshandy.rentservice.persistence.model.Premise;
import com.pshandy.rentservice.persistence.model.Request;
import com.pshandy.rentservice.persistence.repository.PremiseRepository;
import com.pshandy.rentservice.web.dto.SearchDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class SearchController {

    private final PremiseRepository premiseRepository;

    public SearchController(@Autowired PremiseRepository premiseRepository) {
        this.premiseRepository = premiseRepository;
    }


    @GetMapping("/homepage")
    public String home(Model model) {
        model.addAttribute("searchDto", new SearchDto());
        return "homepage";
    }

    @PostMapping("/homepage")
    public ModelAndView search(@ModelAttribute("searchDto") @Valid final SearchDto searchDto) {
        var premises = premiseRepository.findAll();
        ModelAndView mav = new ModelAndView("/filter");
        mav.addObject("premises",
                StreamSupport.stream(premises.spliterator(), false)
                        .filter(item -> {
                                    return item.getArea() >= searchDto.getLowerSquare()
                                            && item.getArea() <= searchDto.getUpperSquare()
                                            && item.getPrice() >= searchDto.getLowerBudgetLimit()
                                            && item.getPrice() <= searchDto.getUpperBudgetLimit()
                                            && item.getInternetPresent() == searchDto.getInternetPresent()
                                            && item.getZone().equals(searchDto.getZone())
                                            && !item.getIsOccupied(); }
                                ).collect(Collectors.toList())
        );
        return mav;
    }

    @GetMapping("/room/{id}")
    public ModelAndView showEditContract(RedirectAttributes redirectAttributes,
                                         @PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("/premiseRooms");
        Premise premise = premiseRepository.findById(id).get();
        mav.addObject("premise", premise);
        mav.addObject("rooms", premise.getRooms());
        mav.addObject("request", new Request());
        return mav;
    }


}
