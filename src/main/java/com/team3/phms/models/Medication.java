package com.team3.phms.models;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "medications")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String name;

    @Column()
    private String dosage;

    @Column
    private Date startData;

    @Column
    private String frequency;

    @Column
    private String time;

    @Column
    private String frequencyDay;

    public Medication(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_medications",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id"))
    private Set<User> users = new HashSet<>();

    public Medication() {

    }
}
