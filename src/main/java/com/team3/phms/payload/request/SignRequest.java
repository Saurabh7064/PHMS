package com.team3.phms.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignRequest {
    @NotBlank
    private String bloodPressure;

    @NotBlank
    private String glucoseLevel;

    @NotBlank
    private String cholesterol;
}
