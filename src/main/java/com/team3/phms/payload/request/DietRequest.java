package com.team3.phms.payload.request;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
public class DietRequest {
    @NotBlank
    private String height;

    @NotBlank
    private String weight;

    @NotBlank
    private String plan;
}
