package com.pshandy.rentservice.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private Integer discount;

    private Date startDate;

    private Date endDate;

    private Boolean isActive;

    @ManyToOne
    private Premise premise;

    @ManyToOne
    private User user;

}
