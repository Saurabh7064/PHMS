package com.team3.phms.controllers;

import com.team3.phms.advice.Response;
import com.team3.phms.models.Vital;
import com.team3.phms.models.User;
import com.team3.phms.payload.request.VitalRequest;
import com.team3.phms.service.UserService;
import com.team3.phms.service.VitalService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class VitalController {
    final UserService userService;
    final VitalService vitalService;

    public VitalController(UserService userService, VitalService vitalService) {
        this.userService = userService;
        this.vitalService = vitalService;
    }

    @GetMapping("/vital/{id}")
    @PreAuthorize("hasRole('USER')")
    public Response<?> GetSign(@PathVariable("id") Long id) {
        User user = userService.GetCurrentUser();
        Optional<Vital> vital = vitalService.GetVitalByIdAuth(id, user);
        if (!vital.isPresent()) {
            return Response.fail(400, "wrong id or not authed");
        }
        return Response.success(vital);
    }

    @PostMapping("/vital")
    @PreAuthorize("hasRole('USER')")
    public Response<?> CreateVital(@Valid @RequestBody VitalRequest vitalRequest) {
        User user = userService.GetCurrentUser();
        Vital vital = vitalService.CreateVital(vitalRequest.getName(), vitalRequest.getFrequency(), vitalRequest.getFrequencyDay(),
                vitalRequest.getMetrics(), vitalRequest.getStartDate(), vitalRequest.getTime(),user);
        if (vital == null) {
            return Response.fail(400, "failed to create sign");
        }

        return Response.success(vital);
    }

    @PutMapping("/vital/{id}")
    @PreAuthorize("hasRole('USER')")
    public Response<?> UpdateVital(@PathVariable("id") Long id, @Valid @RequestBody VitalRequest vitalRequest) {
        User user = userService.GetCurrentUser();
        Optional<Vital> vital = vitalService.GetVitalByIdAuth(id, user);
        if (!vital.isPresent()) {
            return Response.fail(400, "wrong id or not authed");
        }
        Vital vitaled = vitalService.UpdateVital(vital.get(), vitalRequest.getName(), vitalRequest.getFrequency(), vitalRequest.getFrequencyDay(),
                vitalRequest.getMetrics(), vitalRequest.getStartDate(), vitalRequest.getTime());
        return Response.success(vitaled);
    }

    @DeleteMapping("/vital/{id}")
    @PreAuthorize("hasRole('USER')")
    public Response<?> DeleteSign(@PathVariable("id") Long id) {
        User user = userService.GetCurrentUser();
        Optional<Vital> vital = vitalService.GetVitalByIdAuth(id, user);
        if (!vital.isPresent()) {
            return Response.fail(400, "wrong id or not authed");
        }
        Boolean flag = vitalService.DeleteVital(vital.get());
        return Response.success(flag);
    }
}
