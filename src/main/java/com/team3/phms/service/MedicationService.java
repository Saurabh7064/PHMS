package com.team3.phms.service;

import com.team3.phms.models.Medication;
import com.team3.phms.models.User;
import com.team3.phms.repository.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationService {
    final MedicationRepository medicationRepository;

    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<Medication> GetAll() {
        return medicationRepository.findAll();
    }

    public Optional<Medication> GetMedicationByIdAuth(Long id, User user) {
        return medicationRepository.findById(id);
    }

    public Medication Create(String name, User user) {
        Medication medication = new Medication(name);
        medication.getUsers().add(user);
        return medicationRepository.save(medication);
    }

    public List<Medication> FindByNameLike(String name) {
        return medicationRepository.findAllByNameLike("%" + name + "%");
    }
}
