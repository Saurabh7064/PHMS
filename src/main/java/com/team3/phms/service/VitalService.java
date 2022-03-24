package com.team3.phms.service;

import com.team3.phms.models.Sign;
import com.team3.phms.models.User;
import com.team3.phms.models.Vital;
import com.team3.phms.repository.VitalRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VitalService {
    final VitalRepository vitalRepository;

    public VitalService(VitalRepository vitalRepository) {
        this.vitalRepository = vitalRepository;
    }

    public List<Vital> GetAllVitalByUser(User user) {
        return vitalRepository.findAllByUser(user);
    }

    public Optional<Vital> GetVitalByIdAuth(Long id, User user) {
        Optional<Vital> vital = vitalRepository.findById(id);
        if (!vital.isPresent() || vital.get().getUser()!=user) {
            return Optional.empty();
        }
        return vital;
    }

    public List<Vital> GetAll() {
        return vitalRepository.findAll();
    }

    public Vital CreateVital(String name, String frequency, String frequencyDay, String metrics, Date startDate, Date time, User user) {
        Vital vital = new Vital(name, frequency, frequencyDay, metrics, startDate, time);
        vital.setUser(user);
        return vitalRepository.save(vital);
    }

    public Vital UpdateVital(Vital vital, String name, String frequency, String frequencyDay, String metrics, Date startDate, Date time) {
        vital.setName(name);
        vital.setFrequency(frequency);
        vital.setFrequencyDay(frequencyDay);
        vital.setMetrics(metrics);
        vital.setStartDate(startDate);
        vital.setTime(time);
        return vitalRepository.save(vital);
    }

    public boolean DeleteVital(Vital vital) {
        vitalRepository.delete(vital);
        return true;
    }
}