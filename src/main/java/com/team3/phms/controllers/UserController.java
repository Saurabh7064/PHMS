package com.team3.phms.controllers;

import com.team3.phms.Advice.Response;
import com.team3.phms.models.User;
import com.team3.phms.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
    final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/{id}")
    public Response<?> GetUserById(@PathVariable("id") long id) {
        Optional<User> userData = userRepository.findUserById(id);
        if (!userData.isPresent()) {
            return Response.fail(404, "fail to find user");
        }

        return Response.success(userData);
    }

    @GetMapping("/user")
    public Response<?> GetUser(@RequestParam(required = false) String username) {
        List<User> users = new ArrayList<>();
        if (username == null)
            users.addAll(userRepository.findAll());
        else
            users.addAll(userRepository.findByUsernameContaining(username));
        if (users.isEmpty()) {
            return Response.fail(404, "fail to find user");
        }
        return Response.success(users);
    }
}
