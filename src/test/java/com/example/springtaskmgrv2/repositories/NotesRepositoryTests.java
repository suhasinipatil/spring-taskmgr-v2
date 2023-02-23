package com.example.springtaskmgrv2.repositories;

import com.example.springtaskmgrv2.entities.NoteEntity;
import com.example.springtaskmgrv2.entities.TaskEntity;
import com.example.springtaskmgrv2.respository.NotesRepository;
import com.example.springtaskmgrv2.respository.TasksRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class NotesRepositoryTests {
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
        TaskEntity task = getInCompletedTaskObject();
        var savedTask = tasksRepository.save(task);
        NoteEntity note = new NoteEntity();
        note.setBody("First note");
        note.setTask(task);
        notesRepository.save(note);
        Optional<NoteEntity> optionalNote = notesRepository.findById(note.getId());
        assertTrue(optionalNote.isPresent());
    }

    @Test
    public void testFindNotesByTask(){
        TaskEntity task = getInCompletedTaskObject();
        tasksRepository.save(task);
        NoteEntity note = new NoteEntity();
        note.setBody("First note");
        note.setTask(task);
        notesRepository.save(note);
        var notes = notesRepository.findByTask(task);
        assertNotNull(notes);
        assertEquals(1, notes.size());
    }

    @Test
    public void testDeleteTestById(){
        TaskEntity task = getInCompletedTaskObject();
        tasksRepository.save(task);
        NoteEntity note = new NoteEntity();
        note.setBody("First note");
        note.setTask(task);
        notesRepository.save(note);
        notesRepository.deleteById(note.getId());
        Optional<NoteEntity> optionalNote = notesRepository.findById(note.getId());
        assertFalse(optionalNote.isPresent());
        var notes = notesRepository.findByTask(task);
        assertEquals(0, notes.size());
    }

    @Test
    public void testFindNotesByTaskId(){
        TaskEntity task = getInCompletedTaskObject();
        tasksRepository.save(task);
        NoteEntity note = new NoteEntity();
        note.setBody("First note");
        note.setTask(task);
        notesRepository.save(note);
        var notes = notesRepository.findByTaskId(task.getId());
        assertNotNull(notes);
        assertEquals(task.getId(), notes.get(0).getTask().getId());
    }

    @Test
    public void testDeleteNoteByIdAndTaskId(){
        TaskEntity task = getInCompletedTaskObject();
        tasksRepository.save(task);
        NoteEntity note = new NoteEntity();
        note.setBody("First note");
        note.setTask(task);
        notesRepository.save(note);
        notesRepository.deleteByIdAndTaskId(note.getId(), task.getId());
        Optional<NoteEntity> optionalNote = notesRepository.findById(note.getId());
        assertFalse(optionalNote.isPresent());
        assertNotNull(task);
    }
}
