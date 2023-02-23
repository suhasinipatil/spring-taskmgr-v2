package com.example.springtaskmgrv2.controllers;

import com.example.springtaskmgrv2.dtos.ErrorResponse;
import com.example.springtaskmgrv2.entities.NoteEntity;
import com.example.springtaskmgrv2.services.NotesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {

    final NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getAllNotesByTaskId(@PathVariable Integer taskId){
        return ResponseEntity.ok(notesService.getNotesByTaskId(taskId));
    }

    @PostMapping("")
    public ResponseEntity<NoteEntity> createNoteForGivenTaskId(@PathVariable Integer taskId) throws URISyntaxException {
        var createdNote = notesService.createNoteByTaskId(taskId);
        return ResponseEntity.created(new URI("/tasks/" + taskId + "/notes/"+ createdNote.getId())).body(createdNote);
    }

    @DeleteMapping("/{notesId}")
    public ResponseEntity<NoteEntity> deleteNoteByIdAndTaskId(@PathVariable Integer taskId, @PathVariable Integer notesId){
        return ResponseEntity.accepted().body(notesService.deleteNoteByIdAndTaskId(notesId, taskId));
    }

    @ExceptionHandler(NotesService.NoteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleErrors(NotesService.NoteNotFoundException e){
        return new ResponseEntity<>(
                new ErrorResponse(e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
