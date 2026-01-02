package com.anncho.tasks.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anncho.tasks.domain.entities.TaskList;
import com.anncho.tasks.repositories.TaskListRepository;
import com.anncho.tasks.services.TaskListService;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRespository;

    public TaskListServiceImpl(TaskListRepository taskListRespository) {
        this.taskListRespository = taskListRespository;
    }

    @Override
    public List<TaskList> listTaskLists() {
       return taskListRespository.findAll();
    }
    
}
