package com.pshandy.rentservice.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String roomType;

    private String number;

    private String floorNumber;

    private Boolean unloadingPresent;

    private Boolean serviceExitPresent;

    private Boolean serviceRoomPresent;

    private Integer numberOfOutlets;

    @ManyToOne
    private Premise premise;

}
