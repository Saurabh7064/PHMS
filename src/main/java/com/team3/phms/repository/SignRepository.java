package com.team3.phms.repository;

import com.team3.phms.models.Sign;
import com.team3.phms.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignRepository extends JpaRepository<Sign, Long> {
    List<Sign> findAllByUser(User user);
}