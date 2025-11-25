package com.claucio.pmanager.domain.applicationservice;

import com.claucio.pmanager.domain.entity.Project;
import com.claucio.pmanager.domain.exception.InvalidProjectStatusException;
import com.claucio.pmanager.domain.exception.ProjectNotFoundException;
import com.claucio.pmanager.domain.model.ProjectStatus;
import com.claucio.pmanager.domain.respository.ProjectRepository;
import com.claucio.pmanager.infrastructure.DTO.SaveProjectDataDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
//Cria construtor porque o ProjectRepository é final e todos os atributos final precisa ser iniciados
@Slf4j //Para gerar o objeto log
public class ProjectService {


    private final ProjectRepository projectRepository;

    /**
     * Transactional
     * Faz a chamada de uma transação atômica no banco, ou seja ou tudo é feito ou nada é feito.
     * caso ocorra por exemplo um erro ao tentar excluir um registro nada ira acontecer
     *
     */
    @Transactional //
    public Project createProject(SaveProjectDataDTO saveProjectData) {

        Project project = Project.builder()
                .name(saveProjectData.getName())
                .description(saveProjectData.getDescription())
                .initialDate(saveProjectData.getInitialDate())
                .finalDate(saveProjectData.getFinalDate())
                .status(ProjectStatus.PENDING)
                .build();

        projectRepository.save(project);
        log.info("Project created:" + project);
        return project;
    }

    public Project loadProject(String projectId) {
        return projectRepository
                .findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));
    }

    @Transactional
    public void deleteProject(String projectId) {
        Project project = loadProject(projectId);
        projectRepository.delete(project);
    }

    @Transactional
    public Project updateProject(String projectId, SaveProjectDataDTO saveProjectDataDTO) {
        Project project = loadProject(projectId);
        project.setName(saveProjectDataDTO.getName());
        project.setDescription(saveProjectDataDTO.getDescription());
        project.setInitialDate(saveProjectDataDTO.getInitialDate());
        project.setFinalDate(saveProjectDataDTO.getFinalDate());
        project.setStatus(projectStatus(saveProjectDataDTO.getStatus()));
        return project;
    }

    private ProjectStatus projectStatus(String statusStr) {
        try {
            return ProjectStatus.valueOf(statusStr);
        } catch (IllegalArgumentException | InvalidProjectStatusException e) {
            throw new InvalidProjectStatusException(statusStr);

        }
    }
}
