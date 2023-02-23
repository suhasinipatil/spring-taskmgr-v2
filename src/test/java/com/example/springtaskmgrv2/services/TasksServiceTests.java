package com.example.springtaskmgrv2.services;

import com.example.springtaskmgrv2.entities.TaskEntity;
import com.example.springtaskmgrv2.respository.NotesRepository;
import com.example.springtaskmgrv2.respository.TasksRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TasksServiceTests {

   // @Autowired TasksService tasksService;
    @Autowired TasksRepository tasksRepository;
    @Autowired
    NotesRepository notesRepository;

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
    public void testGetAllTasks(){
        TasksService tasksService = new TasksService(tasksRepository, notesRepository);
        TaskEntity task = new TaskEntity();
        task.setTitle("test task");
        task.setDescription("test description");
        task.setCompleted(false);
        tasksRepository.save(task);
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle("test task");
        taskEntity.setDescription("test description");
        taskEntity.setCompleted(true);
        tasksRepository.save(taskEntity);
        var tasks = tasksService.getAllTasks();
        assertNotNull(tasks);
        assertEquals(2, tasks.size());
    }

    @Test
    public void testGetTaskbyId(){
        TasksService tasksService = new TasksService(tasksRepository, notesRepository);
        TaskEntity task = getCompletedTaskObject();
        var savedTask = tasksRepository.save(task);
        var currentTask = tasksService.getTaskById(savedTask.getId());
        assertNotNull(savedTask);
        assertEquals(savedTask.getId(), currentTask.getId());
        //var exceptionTask = tasksService.getTaskById(300);
        assertThrowsExactly(TasksService.TaskNotFoundException.class, () -> tasksService.getTaskById(300), "Task with id 300 is not found");
    }

    @Test
    public void testCreateTask(){
        TasksService tasksService = new TasksService(tasksRepository, notesRepository);
        TaskEntity task = getCompletedTaskObject();
        var savedTask = tasksService.createTask(task);
        assertNotNull(savedTask);
    }

    @Test
    public void testDeleteTaskById(){
        TasksService tasksService = new TasksService(tasksRepository, notesRepository);
        TaskEntity task = getCompletedTaskObject();
        var savedTask = tasksRepository.save(task);
        var deletedTask = tasksService.deleteTaskById(savedTask.getId());
        assertNotNull(savedTask);
        assertEquals(savedTask.getId(), deletedTask.getId());
    }

    @Test
    public void testUpdateTaskById(){
        TasksService tasksService = new TasksService(tasksRepository, notesRepository);
        TaskEntity task = getCompletedTaskObject();
        var savedTask = tasksRepository.save(task);
        var updatedTask = tasksService.updateTaskById(savedTask.getId(), "updated title", savedTask.getDescription(), savedTask.getDueDate(), false);
        assertEquals("updated title", updatedTask.getTitle());
    }

    @Test
    public void testFindAllTaskByTitle(){
        TasksService tasksService = new TasksService(tasksRepository, notesRepository);
        TaskEntity task = getCompletedTaskObject();
        var savedTask = tasksRepository.save(task);
        var allTasks = tasksService.findAllTasksByTitle(savedTask.getTitle());
        assertNotNull(allTasks);
        assertEquals(savedTask.getTitle(), allTasks.get(0).getTitle());
    }

    @Test
    public void testFindAllTaskByCompleted(){
        TasksService tasksService = new TasksService(tasksRepository, notesRepository);
        TaskEntity task = getCompletedTaskObject();
        var savedTask = tasksRepository.save(task);
        var allTasks = tasksService.findAllTaskByCompleted(savedTask.getCompleted());
        assertNotNull(allTasks);
        assertEquals(savedTask.getCompleted(), allTasks.get(0).getCompleted());
    }
}
