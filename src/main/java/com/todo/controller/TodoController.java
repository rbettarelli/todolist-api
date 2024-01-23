package com.todo.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

import com.todo.controller.dto.TodoDTO;
import com.todo.model.Todo;
import com.todo.service.TodoService;


@RestController
@RequestMapping("/todo")

public record TodoController(TodoService todoService) {

    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> findById(@PathVariable Long id) {
        var todo = todoService.findById(id);
        return ResponseEntity.ok(new TodoDTO(todo));
    }

    @PostMapping
    public ResponseEntity<TodoDTO> create(@RequestBody TodoDTO todoDTO) {
        var todo = todoService.create(todoDTO.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todo.getId())
                .toUri();
        return ResponseEntity.created(location).body(new TodoDTO(todo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDTO> update(@PathVariable Long id, @RequestBody TodoDTO todoDTO) {
        var todo = todoService.update(id, todoDTO.toModel());
        return ResponseEntity.ok(new TodoDTO(todo));
    }

    @PutMapping("/{id}/completed")
    public ResponseEntity<TodoDTO> updateTaskCompleted(@PathVariable Long id,
            @RequestBody Map<String, Boolean> requestBody) {
        Boolean completed = requestBody.get("completed");
        if (completed == null) {
            return ResponseEntity.badRequest().build(); // Lidar com uma solicitação inválida
        }
        Todo todoOptional = todoService.findById(id);
        todoOptional.setCompleted(completed);
        todoService.update(id, todoOptional); // Atualiza a tarefa no banco de dados
        return ResponseEntity.ok(new TodoDTO(todoOptional));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TodoDTO>> findAll() {
        var todo = todoService.findAll();
        var todoDto = todo.stream().map(TodoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(todoDto);

    }

    @GetMapping("/completedCount")
    public ResponseEntity<Integer> countCompletedTasks() {
        int count = todoService.countCompletedTasks();
        return ResponseEntity.ok(count);
    }

}
