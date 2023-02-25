package com.example.springtaskmgrv2.services;

import com.example.springtaskmgrv2.entities.NoteEntity;
import com.example.springtaskmgrv2.entities.TaskEntity;
import com.example.springtaskmgrv2.respository.NotesRepository;
import com.example.springtaskmgrv2.respository.TasksRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class NotesServiceTests {
    @Autowired
    NotesRepository notesRepository;
    @Autowired
    TasksRepository tasksRepository;
    private TaskEntity getInCompletedTaskObject(){
        TaskEntity task = new TaskEntity();
        task.setTitle("test task");
        task.setDescription("test description");
        task.setCompleted(false);
        return task;
    }

    @Test
    public void testCreateNote(){
        NotesService notesService = new NotesService(notesRepository, tasksRepository);
        TaskEntity task = getInCompletedTaskObject();
        var savedTask = tasksRepository.save(task);
        NoteEntity note = new NoteEntity();
        note.setTitle("First title");
        note.setBody("First note");
        note.setTask(task);
        var savedNote = notesService.createNote(note);
        assertNotNull(savedNote);
    }

    @Test
    public void testDeleteNoteByIdAndTaskIdScenario1(){
        NotesService notesService = new NotesService(notesRepository, tasksRepository);
        TaskEntity task = getInCompletedTaskObject();
        var savedTask = tasksRepository.save(task);
        NoteEntity note = new NoteEntity();
        note.setBody("First note");
        note.setTask(task);
        note.setTitle("First title");
        notesRepository.save(note);
        var deletedNote = notesService.deleteNoteByIdAndTaskId(note.getId(), task.getId());
        assertNotNull(deletedNote);
    }

    @Test
    public void testDeleteNoteByIdAndTaskIdScenario2(){
        NotesService notesService = new NotesService(notesRepository, tasksRepository);
        TaskEntity task = getInCompletedTaskObject();
        var savedTask = tasksRepository.save(task);
        NoteEntity note1 = new NoteEntity();
        note1.setBody("First note");
        note1.setTask(task);
        note1.setTitle("First title");
        notesRepository.save(note1);
        NoteEntity note2 = new NoteEntity();
        note2.setBody("First note");
        note2.setTask(task);
        note2.setTitle("First title");
        notesRepository.save(note2);
        var deletedNote = notesService.deleteNoteByIdAndTaskId(note1.getId(), task.getId());
        assertNotNull(deletedNote);
        assertNull(deletedNote.getTask());
    }

    @Test
    public void testCreateNoteByTaskId(){
        NotesService notesService = new NotesService(notesRepository, tasksRepository);
        TaskEntity task = getInCompletedTaskObject();
        var savedTask = tasksRepository.save(task);
        var createdNote = notesService.createNoteByTaskId(savedTask.getId(), "title", "body");
        assertNotNull(createdNote);
        assertEquals(task, createdNote.getTask());
    }

    @Test
    public void testNoteNotFoundException(){
        NotesService notesService = new NotesService(notesRepository, tasksRepository);
        assertThrowsExactly(NotesService.NoteNotFoundException.class, () -> notesService.deleteNoteByIdAndTaskId(300, 200), "Note with id 300 is not found");
        assertThrowsExactly(NotesService.NoteNotFoundException.class, () -> notesService.getNoteByID(200), "Note with id 200 is not found");
        assertThrowsExactly(TasksService.TaskNotFoundException.class, () -> notesService.createNoteByTaskId(200, "title", "body"), "Task with id 200 is not found");
    }

    @Test
    public void testGetNotesByTaskId(){
        NotesService notesService = new NotesService(notesRepository, tasksRepository);
        TaskEntity task = getInCompletedTaskObject();
        var savedTask = tasksRepository.save(task);
        NoteEntity note = new NoteEntity();
        note.setTitle("First title");
        note.setBody("First note");
        note.setTask(task);
        notesRepository.save(note);
        List<NoteEntity> allNotes = notesService.getNotesByTaskId(task.getId());
        assertNotNull(allNotes);
        assertEquals(1, allNotes.size());
    }
}
