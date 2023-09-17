package com.todo.service;


import com.todo.model.Todo;

public interface TodoService extends CrudService<Long, Todo> {

    int countCompletedTasks();
    
}
