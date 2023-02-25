package com.example.springtaskmgrv2.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity(name = "tasks")
@Getter
@Setter
public class TaskEntity extends BaseEntity{
    @Column(name = "title", nullable = false, length = 150)
    //@NotNull
    String title;

    @Column(name = "description", nullable = true, length = 500)
    String description;

    @Column(name = "completed", nullable = false, columnDefinition = "boolean default false")
    //@NotNull
    Boolean completed;

    @Column(name = "dueDate", nullable = true)
    Date dueDate;

    /* @OneToMany(targetEntity = NoteEntity.class, cascade = CascadeType.ALL, mappedBy = "task")
    List<NoteEntity> notes;*/
}
