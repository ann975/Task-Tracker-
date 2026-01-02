package com.anncho.tasks.domain.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


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
    //cascadetype.remove - when deleting tasklist all tasks will be deleted too
    //cascadetype.persist - when we save list any new task it contains will be saved too
    @OneToMany(mappedBy = "taskList", cascade= {
        CascadeType.REMOVE,
        CascadeType.PERSIST

    })
    private List<Task> tasks;

    @Column(name="created", nullable=false)
    private LocalDateTime created;

    @Column(name="upated", nullable=false)
    private LocalDateTime updated;




    
}
