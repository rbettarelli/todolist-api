package com.todo.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todo.controller.dto.ProjectDTO;
import com.todo.service.ProjectService;


@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/project")

public record ProjectController(ProjectService projectService) {

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> findById(@PathVariable Long id) {
        var project = projectService.findById(id);
        return ResponseEntity.ok(new ProjectDTO(project));

    }
    @PostMapping
    public ResponseEntity<ProjectDTO> create(@RequestBody ProjectDTO projectDTO) {
        var project = projectService.create(projectDTO.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(project.getId()).toUri();
        return ResponseEntity.created(location).body(new ProjectDTO(project));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> update(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        var project = projectService.update(id, projectDTO.toModel());
        return ResponseEntity.ok(new ProjectDTO(project));
    }
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> findAll() {
        var project = projectService.findAll();
        var projectDTO = project.stream().map(ProjectDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(projectDTO);
    }
    
}
