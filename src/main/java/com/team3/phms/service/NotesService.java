package com.team3.phms.service;

import com.team3.phms.models.Notes;
import com.team3.phms.models.User;
import com.team3.phms.repository.NotesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotesService   {
    
    final NotesRepository notesRepository;


    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public List<Notes> GetAllNotesByUser(User user) {
        return notesRepository.findAllByUser(user);
    }

    public Optional<Notes> GetNotesByIdAuth(Long id, User user) {
        Optional<Notes> notes = notesRepository.findById(id);
        if (!notes.isPresent() || notes.get().getUser()!=user) {
            return Optional.empty();
        }
        return notes;
    }

    public List<Notes> GetAll() {
        return notesRepository.findAll();
    }

    public Notes CreateNotes(String note, User user) {
        Notes notes = new Notes(note);
        notes.setUser(user);
        return notesRepository.save(notes);
    }

    public Notes UpdateNotes(Notes notes, String note) {
        notes.setNote(note);
        return notesRepository.save(notes);
    }

    public boolean DeleteNotes(Notes notes) {
        notesRepository.delete(notes);
        return true;
    }
}
