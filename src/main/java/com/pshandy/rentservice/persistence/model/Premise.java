package com.pshandy.rentservice.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "premise")
public class Premise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private Integer price;

    private Integer area;

    private String zone;

    private boolean isOccupied;

    private boolean internetPresent;

    @OneToMany(mappedBy = "premise")
    private Set<Room> rooms;

    @OneToMany(mappedBy = "premise")
    private Set<Contract> contracts;

}
