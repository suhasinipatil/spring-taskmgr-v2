package com.example.springtaskmgrv2.services;

import com.example.springtaskmgrv2.entities.NoteEntity;
import com.example.springtaskmgrv2.entities.TaskEntity;
import com.example.springtaskmgrv2.respository.NotesRepository;
import com.example.springtaskmgrv2.respository.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotesService {
    final NotesRepository notesRepository;

    final TasksRepository tasksRepository;

    public NotesService(NotesRepository notesRepository, TasksRepository tasksRepository) {
        this.notesRepository = notesRepository;
        this.tasksRepository = tasksRepository;
    }

    public static class NoteNotFoundException extends RuntimeException{
        public NoteNotFoundException(Integer id) {
            super("Note with id " + id + " is not found");
        }
    }

    public NoteEntity createNote(NoteEntity note){
        return notesRepository.save(note);
    }

    public NoteEntity getNoteByID(Integer nodeId){
        Optional<NoteEntity> notes = notesRepository.findById(nodeId);
        if(notes.isPresent())
            return notes.get();
        throw new NoteNotFoundException(nodeId);
    }

    public NoteEntity createNoteByTaskId(Integer taskId){
        Optional<TaskEntity> tasks = tasksRepository.findById(taskId);
        if(tasks.isPresent()){
            NoteEntity note = new NoteEntity();
            note.setBody("");
            note.setTask(tasks.get());
            return notesRepository.save(note);
        }
        throw new TasksService.TaskNotFoundException(taskId);
    }

    public NoteEntity deleteNoteByIdAndTaskId(Integer noteId, Integer taskId){
        NoteEntity note = getNoteByID(noteId);
        notesRepository.deleteByIdAndTaskId(noteId, taskId);
        return note;
    }

    public List<NoteEntity> getNotesByTaskId(Integer taskId){
        return notesRepository.findByTaskId(taskId);
    }
}
