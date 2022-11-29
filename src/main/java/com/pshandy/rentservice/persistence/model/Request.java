package com.pshandy.rentservice.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Premise premise;

    @ManyToOne
    private User user;

}
