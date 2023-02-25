package com.example.springtaskmgrv2.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "notes")
@Getter
@Setter
public class NoteEntity extends BaseEntity{

    @Column(name = "body", nullable = false, length = 500)
    String body;

    @Column(name ="title", nullable = false, length = 150)
    String title;

    @ManyToOne
    TaskEntity task;
}
