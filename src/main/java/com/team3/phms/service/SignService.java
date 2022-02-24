package com.team3.phms.service;

import com.team3.phms.models.Sign;
import com.team3.phms.models.User;
import com.team3.phms.repository.SignRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SignService {
    final SignRepository signRepository;

    public SignService(SignRepository signRepository) {
        this.signRepository = signRepository;
    }

    public List<Sign> GetAllSignByUser(User user) {
        return signRepository.findAllByUser(user);
    }

    public Optional<Sign> GetSignByIdAuth(Long id, User user) {
        Optional<Sign> sign = signRepository.findById(id);
        if (!sign.isPresent() || sign.get().getUser() != user) {
            return Optional.empty();
        }
        return sign;
    }

    public Optional<Sign> GetSign(Long id) {
        return signRepository.findById(id);
    }

    public List<Sign> GetAll() {
        return signRepository.findAll();
    }

    public Sign CreateSign(String bloodPressure, String glucoseLevel, String cholesterol, User user) {
        Sign sign = new Sign(bloodPressure, glucoseLevel, cholesterol);
        sign.setUser(user);
        return signRepository.save(sign);
    }

    public Sign UpdateSign(Sign sign, String bloodPressure, String glucoseLevel, String cholesterol) {
        sign.setCholesterol(cholesterol);
        sign.setBloodPressure(bloodPressure);
        sign.setGlucoseLevel(glucoseLevel);
        return signRepository.save(sign);
    }

    public boolean DeleteSign(Sign sign) {
        signRepository.delete(sign);
        return true;
    }
}
