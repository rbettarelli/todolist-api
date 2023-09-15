package com.todo.controller.dto;

import java.util.Date;

import org.glassfish.jaxb.core.v2.TODO;

import com.todo.model.Todo;

public record TodoDTO(Long id, String title, Date dueDate, Boolean completed) {

    public TodoDTO(Todo model) {
        this(model.getId(), model.getTitle(), model.getDueDate(), model.isCompleted());
    }

  

    public Todo toModel(){
        Todo model = new Todo();
        model.setId(this.id);
        model.setDueDate(this.dueDate);
        model.setTitle(this.title);
        model.setCompleted(this.completed);
        return model;
    }

   
    
}
