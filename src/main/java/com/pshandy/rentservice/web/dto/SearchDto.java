package com.pshandy.rentservice.web.dto;

import lombok.Data;

@Data
public class SearchDto {

    private Integer lowerSquare;

    private Integer upperSquare;

    private Integer lowerBudgetLimit;

    private Integer upperBudgetLimit;

    private String zone;

    private Boolean internetPresent;

}
