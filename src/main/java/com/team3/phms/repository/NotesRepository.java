package com.team3.phms.repository;

import com.team3.phms.models.Notes;
import com.team3.phms.models.User;
import com.team3.phms.models.Vital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {
    List<Notes> findAllByUser(User user);

}
