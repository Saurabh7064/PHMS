package com.team3.phms.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "signs")
@SQLDelete(sql = "update signs set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class Sign {
    public Sign() {

    }

    public Sign(String bloodPressure, String glucoseLevel, String cholesterol) {
        this.bloodPressure = bloodPressure;
        this.glucoseLevel = glucoseLevel;
        this.cholesterol = cholesterol;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String bloodPressure;

    @Column(length = 64)
    private String glucoseLevel;

    @Column(length = 64)
    private String cholesterol;

    @Basic(optional = false)
    @CreationTimestamp
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date(); // initialize created date

    @UpdateTimestamp
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

    @ManyToOne
    private User user;

    @Column(name="deleted")
    private Integer deleted = 0;
}
