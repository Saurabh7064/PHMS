package com.team3.phms.service;

import com.team3.phms.models.Diet;
import com.team3.phms.models.User;
import com.team3.phms.repository.DietRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DietService {
    final DietRepository dietRepository;

    public DietService(DietRepository dietRepository) {
        this.dietRepository = dietRepository;
    }

    public List<Diet> GetAll() {
        return this.dietRepository.findAll();
    }

    public Optional<Diet> Get(Long id) {
        return this.dietRepository.findById(id);
    }

    public Diet Create(String height, String weight, String plan, Optional<User> user) {
        Diet diet = new Diet(height, weight, plan);
        double h = Double.parseDouble(height);
        double w = Double.parseDouble(weight);
        double b = w/h/h;
        String bmi = String.format("%.2f", b);
        diet.setBmi(bmi);
        diet.setUser(user);
        return dietRepository.save(diet);
    }

    public boolean Delete(Diet diet) {
        dietRepository.delete(diet);
        return true;
    }
}
