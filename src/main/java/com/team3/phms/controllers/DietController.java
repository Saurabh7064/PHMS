package com.team3.phms.controllers;

import com.team3.phms.advice.Response;
import com.team3.phms.models.Diet;
import com.team3.phms.models.User;
import com.team3.phms.payload.request.DietRequest;
import com.team3.phms.service.DietService;
import com.team3.phms.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class DietController {
    final UserService userService;
    final DietService dietService;

    public DietController(UserService userService, DietService dietService) {
        this.userService = userService;
        this.dietService = dietService;
    }

    @GetMapping("/diet")
    public Response<?> GetAll() {
        List<Diet> diets = dietService.GetAll();
        return Response.success(diets);
    }

    @PostMapping("/diet")
    @PreAuthorize("hasRole('USER')")
    public Response<?> Create(@Valid @RequestBody DietRequest dietRequest) {
        User user = userService.GetCurrentUser();
        Diet diet = dietService.Create(dietRequest.getHeight(), dietRequest.getWeight(), dietRequest.getPlan(), user);
        return Response.success(diet);
    }

    @DeleteMapping("/diet/{id}")
    @PreAuthorize("hasRole('USER')")
    public Response<?> Delete(@PathVariable("id") Long id) {
        Optional<Diet> diet = dietService.Get(id);
        return Response.success(dietService.Delete(diet.get()));
    }
}