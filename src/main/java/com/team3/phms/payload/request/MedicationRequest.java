package com.team3.phms.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MedicationRequest {
    @NotBlank
    private String name;
}
