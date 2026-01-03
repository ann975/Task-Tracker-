package com.anncho.tasks.mappers.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.anncho.tasks.domain.dto.TaskListDto;
import com.anncho.tasks.domain.entities.Task;
import com.anncho.tasks.domain.entities.TaskList;
import com.anncho.tasks.domain.entities.TaskStatus;
import com.anncho.tasks.mappers.TaskListMapper;
import com.anncho.tasks.mappers.TaskMapper;

@Component
public class TaskListMapperImpl implements TaskListMapper{

    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }
    
    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
            taskListDto.id(),
            taskListDto.title(),
            taskListDto.description(),
            // has option of being null
            // if not null, Optional contain list
            // if null, Optional.empty()
            Optional.ofNullable(taskListDto.tasks())
                .map(tasks -> tasks.stream() //converts list to stream
                    .map(taskMapper:: fromDto) 
                    //method reference - for each element in stream call taskMapper.fromDto(element) and use results
                    // takes each TaskDto and turns into Task 
                    .toList() // collects results into list
                ).orElse(null),
        null,
        null
        
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
            taskList.getId(),
            taskList.getTitle(),
            taskList.getDescription(),
            Optional.ofNullable(taskList.getTasks())
                .map(List::size)
                .orElse(0),
            calculateTaskListProgress(taskList.getTasks()),
            Optional.ofNullable(taskList.getTasks())
                .map(tasks -> tasks.stream()
                    .map(taskMapper::toDto)
                    .toList()
                ).orElse(null)
            
            
        );
    }

    private Double calculateTaskListProgress(List<Task> tasks){
        if(null == tasks){
            return null;
        }

        long closedtaskCount = tasks.stream()
                                    .filter(task-> TaskStatus.CLOSED == task.getStatus())
                                    .count();

        return (double) closedtaskCount / tasks.size();
    }
    
}
