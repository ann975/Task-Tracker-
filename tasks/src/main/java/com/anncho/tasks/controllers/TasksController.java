package com.anncho.tasks.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anncho.tasks.domain.dto.TaskDto;
import com.anncho.tasks.domain.entities.Task;
import com.anncho.tasks.mappers.TaskMapper;
import com.anncho.tasks.services.TaskService;

@RestController
@RequestMapping(path="/api/task-lists/{task_list_id}/tasks")
public class TasksController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TasksController(TaskMapper taskMapper, TaskService taskService) {
        this.taskMapper = taskMapper;
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDto> listTasks(@PathVariable("task_list_id") UUID taskListId){
        return taskService.listTasks(taskListId)
            .stream()
            .map(taskMapper::toDto)
            .toList();
    }

    @PostMapping
    public TaskDto createTask(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TaskDto taskDto){

        Task createdTask = taskService.createTask(
            taskListId, 
            taskMapper.fromDto(taskDto));

        return taskMapper.toDto(createdTask);
     
    }


    
}
