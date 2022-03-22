package com.team3.phms.service;

import com.team3.phms.models.Medication;
import com.team3.phms.models.Sign;
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
        Optional<Medication> medication = medicationRepository.findById(id);
        if (!medication.isPresent() || medication.get().getUsers().containsValue(user)) {
            return Optional.empty();
        }
        return medication;

    }
}
