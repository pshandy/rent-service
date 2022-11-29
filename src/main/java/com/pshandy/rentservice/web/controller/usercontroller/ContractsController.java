package com.pshandy.rentservice.web.controller.usercontroller;

import com.pshandy.rentservice.persistence.model.Contract;
import com.pshandy.rentservice.persistence.model.User;
import com.pshandy.rentservice.persistence.repository.ContractRepository;
import com.pshandy.rentservice.security.AppUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class ContractsController {

    private final ContractRepository contractRepository;

    public ContractsController(@Autowired ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @GetMapping("/user/contract")
    public String showMyContract(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ((AppUserDetails)principal).getUser();
        model.addAttribute("contracts",
                StreamSupport
                        .stream(contractRepository.findAll().spliterator(), false)
                        .filter(contract -> contract.getUser().getId().equals(user.getId()))
                        .collect(Collectors.toList())
        );
        return "/user/contract";
    }


}
