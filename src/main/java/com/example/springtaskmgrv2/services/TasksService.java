package com.example.springtaskmgrv2.services;

import com.example.springtaskmgrv2.entities.TaskEntity;
import com.example.springtaskmgrv2.respository.NotesRepository;
import com.example.springtaskmgrv2.respository.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TasksService {
    final TasksRepository tasksRepository;
    final NotesRepository notesRepository;

    public static class TaskNotFoundException extends RuntimeException{
        public TaskNotFoundException(Integer id) {
            super("Task with id " + id + " is not found");
        }
    }

    public TasksService(TasksRepository tasksRepository, NotesRepository notesRepository) {
        this.tasksRepository = tasksRepository;
        this.notesRepository = notesRepository;
    }

    public List<TaskEntity> getAllTasks(){
        return tasksRepository.findAll();
    }

    public TaskEntity getTaskById(Integer id){
        Optional<TaskEntity> task = tasksRepository.findById(id);
        if(task.isPresent())
            return task.get();
        throw new TaskNotFoundException(id);
    }

    public TaskEntity createTask(TaskEntity task){
        return tasksRepository.save(task);
    }

    public TaskEntity deleteTaskById(Integer id){
        TaskEntity task = getTaskById(id);
        tasksRepository.deleteById(id);
        return task;
    }

    public TaskEntity updateTaskById(Integer id, String title, String description, Date dueDate, Boolean completed){
        TaskEntity task = getTaskById(id);
        if(title != null) task.setTitle(title);
        if(description != null) task.setDescription(description);
        if(dueDate != null) task.setDueDate(dueDate);
        if(completed != null) task.setCompleted(completed);
        return tasksRepository.save(task);
    }

    public List<TaskEntity> findAllTasksByTitle(String title){
        return tasksRepository.findTaskEntitiesByTitle(title);
    }

    public List<TaskEntity> findAllTaskByCompleted(Boolean completed){
        return tasksRepository.findAllByCompleted(completed);
    }

}
