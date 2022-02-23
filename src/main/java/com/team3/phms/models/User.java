package com.team3.phms.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email")
    })
public class User {
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
  @Size(max = 120)
  private String password;

  @Column(name = "age", length = 2)
  private Integer age;

  @Column(name = "gender", length = 4)
  private String gender;

  @Column(name = "weight", length = 8)
  private String weight;

  @Column(name = "height", length = 8)
  private String height;

  public User() {

  }

  public User(String username, String email, String password, Integer age, String gender, String weight, String height) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.age = age;
    this.gender = gender;
    this.weight = weight;
    this.height = height;
  }

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<Sign> signsList = new ArrayList<>();

  @ManyToOne()
  @JoinColumn(name="doctor_id")
  private Doctor doctor;

  @ManyToOne()
  @JoinColumn(name="diet_id")
  private Diet diet;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_doctors",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "doctor_id"))
  private Set<Doctor> doctors = new HashSet<>();

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_medications",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "medication_id"))
  private Set<Medication> medication = new HashSet<>();
}
