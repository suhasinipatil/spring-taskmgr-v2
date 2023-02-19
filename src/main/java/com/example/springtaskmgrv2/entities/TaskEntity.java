package com.example.springtaskmgrv2.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "tasks")
public class TaskEntity extends BaseEntity{
    @Column(name = "title", nullable = false, length = 150)
    String title;

    @Column(name = "description", nullable = true, length = 500)
    String description;

    @Column(name = "completed", nullable = false, columnDefinition = "boolean default false")
    Boolean completed;

    @Column(name = "dueDate", nullable = true)
    Date dueDate;
}
