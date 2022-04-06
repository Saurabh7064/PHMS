package com.team3.phms.controllers;

import com.team3.phms.advice.Response;
import com.team3.phms.models.User;
import com.team3.phms.models.Notes;
import com.team3.phms.payload.request.NotesRequest;
import com.team3.phms.service.NotesService;
import com.team3.phms.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class NotesController {

    final UserService userService;
    final NotesService notesService;

    public NotesController(UserService userService, NotesService notesService) {
        this.userService = userService;
        this.notesService = notesService;
    }

    @GetMapping("/notes")
    public Response<?> GetAllNotes() {
        List<Notes> notes = notesService.GetAll();
        return Response.success(notes);
    }

    @GetMapping("/notes/feed")
    @PreAuthorize("hasRole('USER')")
    public Response<?> GetSignFeed() {
        User user = userService.GetCurrentUser();
        List<Notes> notess = notesService.GetAllNotesByUser(user);
        return Response.success(notess);
    }

    @GetMapping("/notes/{id}")
    @PreAuthorize("hasRole('USER')")
    public Response<?> GetSign(@PathVariable("id") Long id) {
        User user = userService.GetCurrentUser();
        Optional<Notes> notes = notesService.GetNotesByIdAuth(id, user);
        if (!notes.isPresent()) {
            return Response.fail(400, "wrong id or not authed");
        }
        return Response.success(notes);
    }

    @PostMapping("/notes")
    @PreAuthorize("hasRole('USER')")
    public Response<?> CreateNotes(@Valid @RequestBody NotesRequest notesRequest) {
        User user = userService.GetCurrentUser();
        Notes notes = notesService.CreateNotes(notesRequest.getNotes(),user);
        if (notes == null) {
            return Response.fail(400, "failed to create sign");
        }

        return Response.success(notes);
    }

    @PutMapping("/notes/{id}")
    @PreAuthorize("hasRole('USER')")
    public Response<?> UpdateNotes(@PathVariable("id") Long id, @Valid @RequestBody NotesRequest notesRequest) {
        User user = userService.GetCurrentUser();
        Optional<Notes> notes = notesService.GetNotesByIdAuth(id, user);
        if (!notes.isPresent()) {
            return Response.fail(400, "wrong id or not authed");
        }
        Notes notesed = notesService.UpdateNotes(notes.get(), notesRequest.getNotes());
        return Response.success(notesed);
    }

    @DeleteMapping("/notes/{id}")
    @PreAuthorize("hasRole('USER')")
    public Response<?> DeleteSign(@PathVariable("id") Long id) {
        User user = userService.GetCurrentUser();
        Optional<Notes> notes = notesService.GetNotesByIdAuth(id, user);
        if (!notes.isPresent()) {
            return Response.fail(400, "wrong id or not authed");
        }
        Boolean flag = notesService.DeleteNotes(notes.get());
        return Response.success(flag);
    }
    
}
