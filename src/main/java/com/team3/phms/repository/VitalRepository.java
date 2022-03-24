package com.team3.phms.repository;

import com.team3.phms.models.Sign;
import com.team3.phms.models.User;
import com.team3.phms.models.Vital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VitalRepository extends JpaRepository<Vital, Long> {
    List<Vital> findAllByUser(User user);
}
