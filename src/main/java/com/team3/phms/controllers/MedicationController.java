package com.team3.phms.controllers;

import com.team3.phms.advice.Response;
import com.team3.phms.models.Medication;
import com.team3.phms.models.User;
import com.team3.phms.payload.request.MedicationRequest;
import com.team3.phms.service.MedicationService;
import com.team3.phms.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MedicationController {
    final UserService userService;
    final MedicationService medicationService;

    public MedicationController(UserService userService, MedicationService medicationService) {
        this.userService = userService;
        this.medicationService = medicationService;
    }

    @GetMapping("/medication/getAll")
    public Response<?> GetAll() {
        List<Medication> medications = medicationService.GetAll();
        return Response.success(medications);
    }

    @GetMapping("/medication/{id}")
    @PreAuthorize("hasRole('USER')")
    public Response<?> Get(@PathVariable("id") Long id) {
        User user = userService.GetCurrentUser();
        Optional<Medication> medication = medicationService.GetMedicationByIdAuth(id, user);
        if (!medication.isPresent()) {
            return Response.fail(400, "wrong id or not authed");
        }
        return Response.success(medication);
    }

    @PostMapping("/medication")
    @PreAuthorize("hasRole('USER')")
    public Response<?> Create(@Valid @RequestBody MedicationRequest medicationRequest) {
        User user = userService.GetCurrentUser();
        Medication medication = medicationService.Create(medicationRequest.getName(), user);
        if (medication == null) {
            return Response.fail(400, "failed to create medication");
        }

        return Response.success(medication);
    }
}
