package com.claucio.pmanager.infrastructure.DTO;

import com.claucio.pmanager.domain.model.ProjectStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.UUID;

@Data
public class SaveProjectDataDTO {

    private final String name;
    private final String description;
    private final LocalDate initialDate;
    private final LocalDate finalDate;
    private final String status;
}
