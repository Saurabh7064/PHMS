package com.team3.phms.service;

import com.team3.phms.models.Appointment;
import com.team3.phms.models.User;
import com.team3.phms.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    final AppointmentRepository appointmentRepository;


    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> GetAllAppointmentByUser(User user) {
        return appointmentRepository.findAllByUser(user);
    }

    public Optional<Appointment> GetAppointmentByIdAuth(Long id, User user) {
        Optional<Appointment> appointments = appointmentRepository.findById(id);
        if (!appointments.isPresent() || appointments.get().getUser()!=user) {
            return Optional.empty();
        }
        return appointments;
    }

    public List<Appointment> GetAll() {
        return appointmentRepository.findAll();
    }

    public Appointment CreateAppointment(String doctorName,String location,String time, User user) {
        Appointment appointments = new Appointment(doctorName,location,time);
        appointments.setUser(user);
        return appointmentRepository.save(appointments);
    }

    public Appointment UpdateAppointment(Appointment appointments, String doctorName,String location,String time, User user) {
        appointments.setDoctorName(doctorName);
        appointments.setLocation(location);
        appointments.setTime(time);
        return appointmentRepository.save(appointments);
    }

    public boolean DeleteAppointment(Appointment appointments) {
        appointmentRepository.delete(appointments);
        return true;
    }
}
