package com.example.springtaskmgrv2.repositories;

import com.example.springtaskmgrv2.entities.TaskEntity;
import com.example.springtaskmgrv2.respository.TasksRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class TasksRepositoryTests {
    @Autowired
    TasksRepository tasksRepository;

    @Test
    public void testCreateTask(){
        TaskEntity task = new TaskEntity();
        task.setTitle("test task");
        task.setDescription("test description");
        task.setCompleted(false);
        //task.setDueDate(new Date("21-02-2023"));
        var savedTask = tasksRepository.save(task);
        assertNotNull(savedTask);
    }
}
