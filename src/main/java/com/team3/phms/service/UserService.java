package com.team3.phms.service;

import com.team3.phms.models.User;
import com.team3.phms.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User GetCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();

        Optional<User> user = userRepository.findByUsername(username);
        return user.get();
    }

    public Optional<User> GetUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public List<User> GetUser(String username) {
        List<User> users = new ArrayList<>();
        if (username == null)
            users.addAll(userRepository.findAll());
        else
            users.addAll(userRepository.findByUsernameContaining(username));
        return users;
    }
}
