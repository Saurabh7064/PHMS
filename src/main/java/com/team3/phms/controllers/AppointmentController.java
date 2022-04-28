package com.team3.phms.controllers;
import com.team3.phms.advice.Response;
import com.team3.phms.models.Appointment;
import com.team3.phms.models.User;
import com.team3.phms.payload.request.AppointmentRequest;
import com.team3.phms.service.AppointmentService;
import com.team3.phms.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AppointmentController {
    final UserService userService;
    final AppointmentService appointmentService;

    public AppointmentController(UserService userService, AppointmentService appointmentService) {
        this.userService = userService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointment")
    public Response<?> GetAllAppointment() {
        List<Appointment> appointment = appointmentService.GetAll();
        return Response.success(appointment);
    }

    @GetMapping("/appointment/feed")
    @PreAuthorize("hasRole('USER')")
    public Response<?> GetSignFeed() {
        User user = userService.GetCurrentUser();
        List<Appointment> appointments = appointmentService.GetAllAppointmentByUser(user);
        return Response.success(appointments);
    }

    @GetMapping("/appointment/{id}")
    @PreAuthorize("hasRole('USER')")
    public Response<?> GetSign(@PathVariable("id") Long id) {
        User user = userService.GetCurrentUser();
        Optional<Appointment> appointment = appointmentService.GetAppointmentByIdAuth(id, user);
        if (!appointment.isPresent()) {
            return Response.fail(400, "wrong id or not authed");
        }
        return Response.success(appointment);
    }

    @PostMapping("/appointment")
    @PreAuthorize("hasRole('USER')")
    public Response<?> CreateAppointment(@Valid @RequestBody AppointmentRequest appointmentRequest) {
        User user = userService.GetCurrentUser();
        Appointment appointment = appointmentService.CreateAppointment(appointmentRequest.getDoctorName(),appointmentRequest.getLocation(),appointmentRequest.getTime(),user);
        if (appointment == null) {
            return Response.fail(400, "failed to create sign");
        }

        return Response.success(appointment);
    }

    @PutMapping("/appointment/{id}")
    @PreAuthorize("hasRole('USER')")
    public Response<?> UpdateAppointment(@PathVariable("id") Long id, @Valid @RequestBody AppointmentRequest appointmentRequest) {
        User user = userService.GetCurrentUser();
        Optional<Appointment> appointment = appointmentService.GetAppointmentByIdAuth(id, user);
        if (!appointment.isPresent()) {
            return Response.fail(400, "wrong id or not authed");
        }
        Appointment appointmented = appointmentService.UpdateAppointment(appointment.get(), appointmentRequest.getDoctorName(),appointmentRequest.getLocation(),appointmentRequest.getTime(),user);
        return Response.success(appointmented);
    }

    @DeleteMapping("/appointment/{id}")
    @PreAuthorize("hasRole('USER')")
    public Response<?> DeleteSign(@PathVariable("id") Long id) {
        User user = userService.GetCurrentUser();
        Optional<Appointment> appointment = appointmentService.GetAppointmentByIdAuth(id, user);
        if (!appointment.isPresent()) {
            return Response.fail(400, "wrong id or not authed");
        }
        Boolean flag = appointmentService.DeleteAppointment(appointment.get());
        return Response.success(flag);
    }
}
