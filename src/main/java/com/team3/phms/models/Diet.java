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
    private String height;

    @Column(length = 64)
    private String bmi;

    @Column(length = 64)
    private String weight;

    @Column(length = 255)
    private String plan;

    @ManyToOne
    private User user;

    public Diet(String height, String weight, String plan) {
        this.height = height;
        this.weight = weight;
        this.plan = plan;
    }

    public Diet() {

    }
}
