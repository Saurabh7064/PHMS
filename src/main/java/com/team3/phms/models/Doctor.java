package com.team3.phms.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 50)
    private String phone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_doctors",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();
}
