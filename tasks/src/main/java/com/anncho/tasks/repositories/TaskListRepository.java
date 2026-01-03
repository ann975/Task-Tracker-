package com.anncho.tasks.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anncho.tasks.domain.entities.TaskList;

// type of entity - TaskList
// type of task list id - UUID 
@Repository
public interface TaskListRepository extends JpaRepository<TaskList, UUID>{

}
