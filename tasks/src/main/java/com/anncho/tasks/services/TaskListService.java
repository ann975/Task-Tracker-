package com.anncho.tasks.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.anncho.tasks.domain.entities.TaskList;

public interface TaskListService {
    List<TaskList> listTaskLists();
    TaskList createTasklist(TaskList taskList);
    Optional<TaskList> getTaskList(UUID id);
    TaskList updateTaskList(UUID taskListId, TaskList taskList);
}
