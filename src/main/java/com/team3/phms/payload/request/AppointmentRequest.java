package com.team3.phms.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AppointmentRequest {
    @NotBlank
    private String location;
    @NotBlank
    private String time;
    @NotBlank
    private String DoctorName;
}

