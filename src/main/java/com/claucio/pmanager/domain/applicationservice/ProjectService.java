package com.claucio.pmanager.domain.applicationservice;

import com.claucio.pmanager.domain.entity.Project;
import com.claucio.pmanager.domain.model.ProjectStatus;
import com.claucio.pmanager.domain.respository.ProjectRepository;
import com.claucio.pmanager.infrastructure.DTO.SaveProjectDataDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //Cria construtor porque o ProjectRepository é final e todos os atributos final precisa ser iniciados
public class ProjectService {

    private final ProjectRepository projectRepository;

    /** Transactional
     * Faz a chamada de uma transação atômica no banco, ou seja ou tudo é feito ou nada é feito.
     * caso ocorra um erro ao tentar excluir um registro nada ira acontecer
     * */
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

        return project;
    }
}
