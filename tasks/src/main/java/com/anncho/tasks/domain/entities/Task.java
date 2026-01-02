package com.anncho.tasks.domain.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity // marks as JPA entity 
@Table(name = "tasks") // specifies name of table created in db
public class Task {

    // instance variables that will map to columns in db
    
    @Id // mark id with id annotation 
    @GeneratedValue(strategy = GenerationType.UUID) 
    // every time we create a task, JPA will generate UUID for us when id is null
    @Column(name = "id", updatable=false, nullable=false)
    // column annotation
    // once created will not be changed
    // will never be null
    // by default, name is the same as instance variable 
    private UUID id;

    @Column(name="title", nullable=false) 
    // default updatable is true 
    private String title;

    @Column(name="description")
    // default nullable is true
    private String description;

    @Column(name="due_date")
    // snake-case for db column
    // camel-case for instance variable in Java 
    private LocalDateTime dueDate;

    @Column(name="status", nullable=false)
    private TaskStatus status;
    // using enums that we created 

    @Column(name="priority", nullable=false)
    private TaskPriority priority;

    @ManyToOne(fetch= FetchType.LAZY) // not loaded to db until actually needed 
    @JoinColumn(name="task_list_id") // will be the column in task table that contains id of task list to which task belongs
    private TaskList taskList;

    @Column(name="created", nullable=false)
    private LocalDateTime created;
    // LocalDateTime doesn't carry time zone but is well suppported by JPA and JSON serialization

    @Column(name="updated", nullable=false)
    private LocalDateTime updated;

    public Task(){
        
    }

   




}
