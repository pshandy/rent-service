package com.pshandy.rentservice.web.controller.usercontroller;

import com.pshandy.rentservice.persistence.model.Contract;
import com.pshandy.rentservice.persistence.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContractsController {

    private final ContractRepository contractRepository;

    public ContractsController(@Autowired ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @GetMapping("/user/contract")
    public String showMyContract(Model model) {
        model.addAttribute("contracts", contractRepository.findAll());
        return "/user/contract";
    }


}
