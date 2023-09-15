package com.todo.controller.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.todo.model.Todo;

public record TodoDTO(Long id, String title, String formattedDueDate, Boolean completed, String priority, String project) {

    public TodoDTO(Todo model) {
        this(model.getId(), model.getTitle(), formatDate(model.getDueDate()), model.isCompleted(), model.getPriority(),
                model.getProject());
    }

    private static String formatDate(Date dueDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(dueDate);
    }

    public Todo toModel() {
        Todo model = new Todo();
        model.setId(this.id);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dueDate = dateFormat.parse(this.formattedDueDate);
            model.setDueDate(dueDate);
        } catch (ParseException e) {
            // Lide com exceções de análise aqui
            e.printStackTrace();
        } // Salvar a data formatada diretamente
        model.setTitle(this.title);
        model.setCompleted(this.completed);
        model.setPriority(this.priority);
        model.setProject(this.project);
        return model;
    }
}
