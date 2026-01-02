package com.anncho.tasks.mappers;

import com.anncho.tasks.domain.dto.TaskDto;
import com.anncho.tasks.domain.entities.Task;


// mappers - handle transformation between objects in domain entities
// and presentation dtos
// using dedicated mappers - separation of concerns, consistent transformation rules, cleaner service and control
// best practice to code to interface than class 
public interface TaskMapper {
    
    Task fromDto(TaskDto task);
    TaskDto toDto(Task task);
}
