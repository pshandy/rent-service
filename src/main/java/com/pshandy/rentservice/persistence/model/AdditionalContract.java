package com.pshandy.rentservice.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class AdditionalContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private Boolean cleaningRequired;

    private Boolean sysAdminRequired;

    private Boolean electricianRequired;

    private Boolean loaderRequired;

    @ManyToOne
    private User user;

}
