package com.todo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.model.Todo;
import com.todo.repository.TodoRepository;
import com.todo.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional(readOnly = true)
    public List<Todo> findAll() {
        return this.todoRepository.findAll();

    }

    @Transactional(readOnly = true)
    public Todo findById(Long id) {
        return this.todoRepository.findById(id).orElseThrow(null);

    }

    @Transactional
    public Todo create(Todo todoToCreate) {
        todoToCreate.getId();
        todoToCreate.getTitle();
        todoToCreate.getDueDate();
        todoToCreate.setCompleted(false);
        return this.todoRepository.save(todoToCreate);
    }

    public Todo update(Long id, Todo todoToUpdate) {
        Todo dbTodo = this.findById(id);
        dbTodo.setTitle(todoToUpdate.getTitle());
        dbTodo.setDueDate(todoToUpdate.getDueDate());
        dbTodo.setCompleted(todoToUpdate.isCompleted());

        return this.todoRepository.save(dbTodo);

    }

    @Transactional
    public void delete(Long id) {
        Todo dbUser = this.findById(id);
        this.todoRepository.delete(dbUser);
    }

}
