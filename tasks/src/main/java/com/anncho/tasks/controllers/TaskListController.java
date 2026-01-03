package com.anncho.tasks.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anncho.tasks.domain.dto.TaskListDto;
import com.anncho.tasks.mappers.TaskListMapper;
import com.anncho.tasks.services.TaskListService;


@RestController
@RequestMapping(path = "/task-lists") // declare path controller operates on
// when getting a HTTP get request for this endpoint 
// will map to @GetMapping endpoint method below 
// will then call task list service , getting list of task lists
// process each one using map 
// convert from task list to task list dto 
public class TaskListController {
    
    // dependencies 
    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    // constructors
    public TaskListController(TaskListMapper taskListMapper, TaskListService taskListService) {
        this.taskListMapper = taskListMapper;
        this.taskListService = taskListService;
    }
    
    // return list task lists 
    @GetMapping
    public List<TaskListDto>  listTaskLists(){
        return taskListService.listTaskLists()
            .stream()
            .map(taskListMapper:: toDto)
            .toList();
    }
    
    
}
