package com.claucio.pmanager.domain.entity;

import com.claucio.pmanager.domain.model.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project")
public class Project {



    @Id
    @GeneratedValue(strategy = UUID) // UUID Gera String unica
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(name = "description", nullable = false, length = 150)
    private String description;

    @Column(name = "initial_date", nullable = false)
    private LocalDate initialDate;

    @Column(name = "final_date", nullable = false)
    private LocalDate finalDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING) // No banco de dados vai mosstrar o valor do texto e não 0, 1 , 2
    private ProjectStatus status;
}
