package com.team3.phms.models;


import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Data
@Entity
@Table(name = "medications")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_medications",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id"))
    private Map<Long, User> users = new HashMap<>();
}
