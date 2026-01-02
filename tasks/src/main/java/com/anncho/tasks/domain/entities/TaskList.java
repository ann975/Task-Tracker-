package com.anncho.tasks.domain.entities;

import java.util.List;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import jakarta.persistence.OneToMany;


@Entity
@Table(name="task_lists")
public class TaskList {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name = "id", updatable=false, nullable=false)
    private UUID id;

    @Column(name="title", nullable=false)
    private String title;

    @Column(name="description")
    private String description;

    // establish relationship between task list and tasks
    // one task list to many tasks 
    // mappedBy - look to instance variable taskList in Task class for more configuration
    @OneToMany(mappedBy = "taskList", cascade= {CascadeType.REMOVE})
    private List<Task> tasks;

    @Column(name="created", nullable=false)
    private LocalDateTime created;

    @Column(name="upated", nullable=false)
    private LocalDateTime updated;




    
}
