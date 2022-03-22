package com.team3.phms.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "vitals")
@SQLDelete(sql = "update vitals set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class Vital {
    public Vital(String name, String frequency, String frequencyDay, String metrics, Date time, Date startDate) {
        this.name = name;
        this.frequency = frequency;
        this.frequencyDay = frequencyDay;
        this.metrics = metrics;
        this.time = time;
        this.startDate = startDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String name;

    @Column(length = 64)
    private String frequency;

    @Column(length = 64)
    private String frequencyDay;

    @Column(length = 64)
    private String metrics;

    @Column()
    private Date time;

    @Column()
    private Date startDate;

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

    public Vital() {

    }
}
