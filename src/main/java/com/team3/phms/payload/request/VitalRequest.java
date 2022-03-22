package com.team3.phms.payload.request;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class VitalRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String frequency;

    @NotBlank
    private String frequencyDay;

    @NotBlank
    private String metrics;

    private Date time;

    private Date startDate;
}
