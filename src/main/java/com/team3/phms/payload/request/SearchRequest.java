package com.team3.phms.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SearchRequest {
    @NotBlank
    private String keyword;
}
