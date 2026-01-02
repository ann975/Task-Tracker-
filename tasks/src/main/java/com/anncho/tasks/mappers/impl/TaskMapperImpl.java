package com.anncho.tasks.mappers.impl;

import org.springframework.stereotype.Component;

import com.anncho.tasks.domain.dto.TaskDto;
import com.anncho.tasks.domain.entities.Task;
import com.anncho.tasks.mappers.TaskMapper;

// impl package - separates implementations from interfaces 

// component - injects any dependencies that we declare for this class 
// and make class a candidate for injection wherever it is declared throughout application
@Component
public class TaskMapperImpl implements TaskMapper{

    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(
            taskDto.id(),
            taskDto.title(),
            taskDto.description(),
            taskDto.dueDate(),
            taskDto.status(),
            taskDto.priority(),
            null,
            null,
            null
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getDueDate(),
            task.getPriority(),
            task.getStatus() 
        );
    }
    
}
