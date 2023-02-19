package com.example.springtaskmgrv2.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "notes")
public class NoteEntity extends BaseEntity{

    @Column(name = "body", nullable = false, length = 500)
    String body;
}
