package com.anncho.tasks.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anncho.tasks.domain.dto.TaskListDto;
import com.anncho.tasks.mappers.TaskListMapper;
import com.anncho.tasks.services.TaskListService;


@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {
    
    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListMapper taskListMapper, TaskListService taskListService) {
        this.taskListMapper = taskListMapper;
        this.taskListService = taskListService;
    }
    
    @GetMapping
    public List<TaskListDto>  listTaskLists(){
        return taskListService.listTaskLists()
            .stream()
            .map(taskListMapper:: toDto)
            .toList();
    }
    
    
}
