package com.anncho.tasks.services;

import java.util.List;

import com.anncho.tasks.domain.entities.TaskList;

public interface TaskListService {
    List<TaskList> listTaskLists();
}
