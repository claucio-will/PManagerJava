package com.claucio.pmanager.infrastructure.controller;

import com.claucio.pmanager.domain.applicationservice.ProjectService;
import com.claucio.pmanager.domain.entity.Project;
import com.claucio.pmanager.infrastructure.DTO.ProjectDTO;
import com.claucio.pmanager.infrastructure.DTO.SaveProjectDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.claucio.pmanager.infrastructure.controller.RestConstants.PATH_PROJECTS;


@RestController
@RequestMapping(PATH_PROJECTS)
@RequiredArgsConstructor
public class ProjectResource {


    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody SaveProjectDataDTO saveProjectDataDTO) {
        Project project = projectService.createProject(saveProjectDataDTO);
        return ResponseEntity.
                created(URI.create(PATH_PROJECTS + project.getId()))
                .body(ProjectDTO.create(project));

    }


}
