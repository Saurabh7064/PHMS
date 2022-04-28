package com.team3.phms.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "notes")
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String note;

    @OneToOne
    private User user;

    public Notes(String note) {
        this.note = note;
    }

    public Notes() {

    }
}
