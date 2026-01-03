package com.anncho.tasks.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.anncho.tasks.domain.entities.Task;
import com.anncho.tasks.repositories.TaskRepository;
import com.anncho.tasks.services.TaskService;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListID) {
        return taskRepository.findByTaskListId(taskListID);
    }
    
}
