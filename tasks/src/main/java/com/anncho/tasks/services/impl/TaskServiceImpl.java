package com.anncho.tasks.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.anncho.tasks.domain.entities.Task;
import com.anncho.tasks.domain.entities.TaskList;
import com.anncho.tasks.domain.entities.TaskPriority;
import com.anncho.tasks.domain.entities.TaskStatus;
import com.anncho.tasks.repositories.TaskListRepository;
import com.anncho.tasks.repositories.TaskRepository;
import com.anncho.tasks.services.TaskService;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListID) {
        return taskRepository.findByTaskListId(taskListID);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if(task.getId() != null){
            throw new IllegalArgumentException("Task already has id.");
        }

        if(task.getTitle() == null || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task must have a title.");
        }

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository.findById(taskListId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid task list ID provided."));

        LocalDateTime now = LocalDateTime.now();

        Task taskToSave = new Task(
            null,
            task.getTitle(),
            task.getDescription(),
            task.getDueDate(),
            taskStatus,
            taskPriority,
            taskList,
            now,
            now

        );

        return taskRepository.save(taskToSave);



    }
    
}
