package com.team3.phms.controllers;

import com.team3.phms.advice.Response;
import com.team3.phms.models.Medication;
import com.team3.phms.models.User;
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

//    @GetMapping("/medicationn/feed")
//    @PreAuthorize("hasRole('USER')")
//    public Response<?> GetMedicationFeed() {
//        User user = userService.GetCurrentUser();
//        List<Medication> medications = medicationService.GetAllmedicationByUser(user);
//        return Response.success(medications);
//    }
//
//    @GetMapping("/medication")
//    @PreAuthorize("hasRole('ADMIN')")
//    public Response<?> GetAllMedication() {
//        List<Medication> medication = medicationService.GetAll();
//        return Response.success(medication);
//    }
//
//    @GetMapping("/medication/{id}")
//    @PreAuthorize("hasRole('USER')")
//    public Response<?> GetMedication(@PathVariable("id") Long id) {
//        User user = userService.GetCurrentUser();
//        Optional<Medication> medication = medicationService.GetMedicationByIdAuth(id, user);
//        if (!medication.isPresent()) {
//            return Response.fail(400, "wrong id or not authed");
//        }
//        return Response.success(medication);
//    }
//
//    @PostMapping("/medication")
//    @PreAuthorize("hasRole('USER')")
//    public Response<?> Createmedication(@Valid @RequestBody medicationRequest medicationRequest) {
//        User user = userService.GetCurrentUser();
//        Medication medication = medicationService.Createmedication(medicationRequest.getBloodPressure(), medicationRequest.getGlucoseLevel(), medicationRequest.getCholesterol(), user);
//        if (medication == null) {
//            return Response.fail(400, "failed to create medication");
//        }
//
//        return Response.success(medication);
//    }
//
//    @PutMapping("/medication/{id}")
//    @PreAuthorize("hasRole('USER')")
//    public Response<?> Updatemedication(@PathVariable("id") Long id, @Valid @RequestBody medicationRequest medicationRequest) {
//        User user = userService.GetCurrentUser();
//        Optional<Medication> medication = medicationService.GetmedicationByIdAuth(id, user);
//        if (!medication.isPresent()) {
//            return Response.fail(400, "wrong id or not authed");
//        }
//        System.out.println(medication);
//        Medication medicationed = medicationService.Updatemedication(medication.get(), medicationRequest.getBloodPressure(), medicationRequest.getGlucoseLevel(), medicationRequest.getCholesterol());
//        return Response.success(medicationed);
//    }
//
//    @DeleteMapping("/medication/{id}")
//    @PreAuthorize("hasRole('USER')")
//    public Response<?> Deletemedication(@PathVariable("id") Long id) {
//        User user = userService.GetCurrentUser();
//        Optional<Medication> medication = medicationService.GetmedicationByIdAuth(id, user);
//        if (!medication.isPresent()) {
//            return Response.fail(400, "wrong id or not authed");
//        }
//        medicationService.Deletemedication(medication.get());
//        return Response.success("True");
//    }
}
