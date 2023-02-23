package com.example.springtaskmgrv2.repositories;

import com.example.springtaskmgrv2.entities.TaskEntity;
import com.example.springtaskmgrv2.respository.TasksRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TasksRepositoryTests {
    @Autowired
    TasksRepository tasksRepository;

    private TaskEntity getInCompletedTaskObject(){
        TaskEntity task = new TaskEntity();
        task.setTitle("test task");
        task.setDescription("test description");
        task.setCompleted(false);
        return task;
    }

    private TaskEntity getCompletedTaskObject(){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle("test task");
        taskEntity.setDescription("test description");
        taskEntity.setCompleted(true);
        return taskEntity;
    }

    @Test
    public void testCreateTask(){
        TaskEntity task = getCompletedTaskObject();
        var savedTask = tasksRepository.save(task);
        assertNotNull(savedTask);
    }

    @Test
    public void testfindAllCompleted(){
        TaskEntity task = getInCompletedTaskObject();
        TaskEntity taskEntity = getCompletedTaskObject();
        tasksRepository.save(task);
        tasksRepository.save(taskEntity);
        var incompletedtasks = tasksRepository.findAllByCompleted(true);
        assertEquals(1, incompletedtasks.size());
        var completedtasks = tasksRepository.findAllByCompleted(false);
        assertEquals(1, completedtasks.size());
    }

    @Test
    public  void testGetAllTasks(){
        TaskEntity task = getInCompletedTaskObject();
        TaskEntity taskEntity = getCompletedTaskObject();
        tasksRepository.save(task);
        tasksRepository.save(taskEntity);
        var alltasks = tasksRepository.findAll();
        assertNotNull(alltasks);
        assertEquals(2, alltasks.size());
    }

    @Test
    public void testSaveTask(){
        TaskEntity task = getInCompletedTaskObject();
        var savedTask = tasksRepository.save(task);
        assertNotNull(savedTask);
    }

    @Test
    public void testDeleteTaskByID(){
        TaskEntity task = getInCompletedTaskObject();
        var savedTask = tasksRepository.save(task);
        tasksRepository.deleteById(savedTask.getId());
        Optional<TaskEntity> deletedTask = tasksRepository.findById(savedTask.getId());
        assertFalse(deletedTask.isPresent());
    }

    @Test
    public void testFindTaskById(){
        TaskEntity task = getInCompletedTaskObject();
        var savedTask = tasksRepository.save(task);
        Optional<TaskEntity> task1 = tasksRepository.findById(savedTask.getId());
        assertTrue(task1.isPresent());
        assertEquals(savedTask.getId(), task1.get().getId());
    }

    @Test
    public void testFindTasksByTitle(){
        TaskEntity task1 = getInCompletedTaskObject();
        var savedTask1 = tasksRepository.save(task1);
        var allTasks = tasksRepository.findTaskEntitiesByTitle(savedTask1.getTitle());
        assertNotNull(allTasks);
        assertEquals(savedTask1.getTitle(), allTasks.get(0).getTitle());
    }
}
