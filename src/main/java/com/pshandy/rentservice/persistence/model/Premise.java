package com.pshandy.rentservice.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    private Boolean isOccupied;

    private Boolean internetPresent;

    private String description;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "premise")
    private Set<Room> rooms;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "premise")
    private Set<Contract> contracts;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "premise")
    private Set<Request> requests;


}
