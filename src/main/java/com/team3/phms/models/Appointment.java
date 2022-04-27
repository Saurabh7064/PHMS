package com.team3.phms.models;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String doctorName;

    public Appointment(String doctorName, String location, String time) {
        this.doctorName = doctorName;
        this.location = location;
        this.time = time;
    }

    @Column(length = 64)
    private String location;

    @Column(length = 64)
    private String time;

    @OneToOne
    private User user;

    public Appointment() {

    }
}
