package com.pshandy.rentservice.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer desiredPeriod;

    private Integer upperBudgetLimit;

    private Integer lowerBudgetLimit;

    private Boolean isAdditionalServicesRequired;

    private String description;

    @ManyToOne
    private User user;

}
