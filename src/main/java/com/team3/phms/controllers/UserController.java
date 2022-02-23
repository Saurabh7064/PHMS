package com.team3.phms.controllers;

import com.team3.phms.advice.Response;
import com.team3.phms.models.User;
import com.team3.phms.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Response<?> GetUserById(@PathVariable("id") long id) {
        Optional<User> userData = userService.GetUserById(id);
        if (!userData.isPresent()) {
            return Response.fail(404, "fail to find user");
        }

        return Response.success(userData);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Response<?> GetUser(@RequestParam(required = false) String username) {
        List<User> users = userService.GetUser(username);
        if (users.isEmpty()) {
            return Response.fail(404, "fail to find user");
        }
        return Response.success(users);
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public Response<?> GetMe() {
        User user = userService.GetCurrentUser();
        return Response.success(user);
    }
}
