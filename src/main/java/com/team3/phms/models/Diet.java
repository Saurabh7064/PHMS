package com.team3.phms.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "diets")
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String foodIntake;

    @Column(length = 64)
    private String calorieCount;

    @Column(length = 64)
    private String weight;
}
