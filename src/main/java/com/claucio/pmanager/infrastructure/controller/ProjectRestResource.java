package com.claucio.pmanager.infrastructure.controller;

import com.claucio.pmanager.domain.applicationservice.ProjectService;
import com.claucio.pmanager.domain.entity.Project;
import com.claucio.pmanager.infrastructure.DTO.ProjectDTO;
import com.claucio.pmanager.infrastructure.DTO.SaveProjectDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.claucio.pmanager.infrastructure.controller.RestConstants.PATH_PROJECTS;


@RestController
@RequestMapping(PATH_PROJECTS)
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class ProjectRestResource {


    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody @Validated SaveProjectDataDTO saveProjectDataDTO) {
        Project project = projectService.createProject(saveProjectDataDTO);
        return ResponseEntity.
                created(URI.create(PATH_PROJECTS + project.getId()))
                .body(ProjectDTO.create(project));


    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> loadProject(@PathVariable("id") String projectId) {
        Project project = projectService.loadProject(projectId);
        return ResponseEntity.ok(ProjectDTO.create(project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable("id") String projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ProjectDTO> updateProject(
            @PathVariable("id") String projectId,
            @RequestBody SaveProjectDataDTO saveProjectDataDTO) {

        Project project = projectService.updateProject(projectId, saveProjectDataDTO);
        return ResponseEntity.ok(ProjectDTO.create(project));


    }

}
