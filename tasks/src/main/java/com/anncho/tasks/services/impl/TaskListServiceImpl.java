package com.anncho.tasks.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.anncho.tasks.domain.entities.TaskList;
import com.anncho.tasks.repositories.TaskListRepository;
import com.anncho.tasks.services.TaskListService;

@Service
public class TaskListServiceImpl implements TaskListService {

    // inject single dependency
    private final TaskListRepository taskListRespository;

    // constructor  
    public TaskListServiceImpl(TaskListRepository taskListRespository) {
        this.taskListRespository = taskListRespository;
    }

    @Override
    public List<TaskList> listTaskLists() {
       return taskListRespository.findAll();
    }

    @Override
    public TaskList createTasklist(TaskList taskList) {

        // make sure task list has not already been created 
        if(taskList.getId() != null){
            throw new IllegalArgumentException("Task list already has an ID.");
        }

        // task list must have title
        if(taskList.getTitle() == null || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("Task list title must be present.");
        }

        LocalDateTime now = LocalDateTime.now();
        // return task list created in db
        return taskListRespository.save(new TaskList(
            null,
            taskList.getTitle(),
            taskList.getDescription(),
            null,
            now,
            now
        ));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRespository.findById(id);
    }
    
}
