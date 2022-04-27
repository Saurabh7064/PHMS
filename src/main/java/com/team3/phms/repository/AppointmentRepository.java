package com.team3.phms.repository;

import com.team3.phms.models.Appointment;
import com.team3.phms.models.User;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByUser(User user);
}
