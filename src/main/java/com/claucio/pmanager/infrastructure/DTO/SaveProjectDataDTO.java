package com.claucio.pmanager.infrastructure.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SaveProjectDataDTO {

    private final String name;
    private final String description;
    private final LocalDate initialDate;
    private final LocalDate finalDate;
    private final String status;
}
