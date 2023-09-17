package com.todo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.model.Project;
import com.todo.repository.ProjectRepository;
import com.todo.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional(readOnly = true)
    public List<Project> findAll() {
        return this.projectRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Project findById(Long id) {
        return this.projectRepository.findById(id).orElseThrow(null);
    }

    @Transactional
    public Project create(Project projectToCreate) {
        projectToCreate.getId();
        projectToCreate.getName();
        return this.projectRepository.save(projectToCreate);
    }

    @Transactional
    public Project update(Long id, Project projectToUpdate) {
        Project dbProject = this.findById(id);
        dbProject.setName(projectToUpdate.getName());
        return this.projectRepository.save(dbProject);
    }

    @Transactional
    public void delete(Long id)  {
        Project dbProject = this.findById(id);
        this.projectRepository.delete(dbProject);
    }  

}
