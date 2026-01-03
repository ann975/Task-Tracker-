package com.anncho.tasks.services;

import java.util.List;
import java.util.UUID;

import com.anncho.tasks.domain.entities.Task;

public interface TaskService {
    List<Task> listTasks(UUID taskListID);
    Task createTask(UUID taskListId, Task task);
}
