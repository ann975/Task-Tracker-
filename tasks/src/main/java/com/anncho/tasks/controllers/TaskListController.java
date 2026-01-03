package com.anncho.tasks.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anncho.tasks.domain.dto.TaskListDto;
import com.anncho.tasks.domain.entities.TaskList;
import com.anncho.tasks.mappers.TaskListMapper;
import com.anncho.tasks.services.TaskListService;



@RestController
@RequestMapping(path = "/api/task-lists") // declare path controller operates on
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

    // take taskListDto that was passed in through request body
    // convert into task list 
    // use task list to create new task list in db via taskListService
    // return task list that was created
    // convert to task list dto and return to caller 
    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto){
        TaskList createdTaskList = taskListService.createTasklist(
            taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(createdTaskList);
    }

    @GetMapping(path = "/{task_list_id}")
    public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID taskListId){
        return taskListService.getTaskList(taskListId).map(taskListMapper::toDto);
    }

    @PutMapping(path = "/{task_list_id}")
    public TaskListDto updateTaskList(
        @PathVariable("task_list_id") UUID taskListId,
        @RequestBody TaskListDto taskListDto 
    
    ){
        TaskList updatedTaskList = taskListService.updateTaskList(
            taskListId, 
            taskListMapper.fromDto(taskListDto)
        );

        return taskListMapper.toDto(updatedTaskList);

    }

    @DeleteMapping(path="/{task_list_id}")
    public void deleteTaskList(@PathVariable("task_list_id") UUID taskListId){
        taskListService.deleteTaskList(taskListId);
    }
    
    
    
}
