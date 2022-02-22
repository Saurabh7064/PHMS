package com.team3.phms.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "signs")
public class Sign {
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
    @JoinColumn(name="user_id")
    private User user;
}
