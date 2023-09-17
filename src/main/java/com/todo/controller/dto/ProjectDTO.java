package com.todo.controller.dto;

import com.todo.model.Project;

public record ProjectDTO (Long id, String name) {

    public ProjectDTO(Project model) {
        this(model.getId(), model.getName());
    }

    public Project toModel() {
        Project model = new Project();
        model.setId(this.id);
        model.setName(this.name);
        return model;
    }

    
}
