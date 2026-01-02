package com.anncho.tasks.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.anncho.tasks.domain.entities.TaskPriority;
import com.anncho.tasks.domain.entities.TaskStatus;

// no created or updated timestamp (internal concerns)
// no TaskList references (handled through URLs in RESTAPI)
// no JPA annotation - dtos are simple data carriers
// constructors, getters, equals, hashCode, toString included as records
// immutable instance variables - no setters
// use Dto to accept task data form API requests and return task data in API responses

public record TaskDto(
    UUID id,
    String title,
    String description,
    LocalDateTime dueDate,
    TaskPriority priority,
    TaskStatus status

) {
    

}
