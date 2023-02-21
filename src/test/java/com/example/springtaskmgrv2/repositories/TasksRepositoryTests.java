package com.example.springtaskmgrv2.repositories;

import com.example.springtaskmgrv2.entities.TaskEntity;
import com.example.springtaskmgrv2.respository.TasksRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        var savedTask = tasksRepository.save(task);
        assertNotNull(savedTask);
    }

    @Test
    public void testfindAllCompleted(){
        TaskEntity task = new TaskEntity();
        task.setTitle("test task");
        task.setDescription("test description");
        task.setCompleted(false);
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle("test task");
        taskEntity.setDescription("test description");
        taskEntity.setCompleted(true);
        tasksRepository.save(task);
        tasksRepository.save(taskEntity);
        var incompletedtasks = tasksRepository.findAllByCompleted(true);
        assertEquals(1, incompletedtasks.size());
        var completedtasks = tasksRepository.findAllByCompleted(false);
        assertEquals(1, completedtasks.size());
    }

    @Test
    public  void testGetAllTasks(){
        TaskEntity task = new TaskEntity();
        task.setTitle("test task");
        task.setDescription("test description");
        task.setCompleted(false);
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle("test task");
        taskEntity.setDescription("test description");
        taskEntity.setCompleted(true);
        tasksRepository.save(task);
        tasksRepository.save(taskEntity);
        var alltasks = tasksRepository.findAll();
        assertNotNull(alltasks);
        assertEquals(2, alltasks.size());
    }
}
