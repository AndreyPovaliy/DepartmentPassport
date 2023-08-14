package com.example.DepartmentPassport.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClinicHrResponse {
    Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    DepartmentResponse departmentResponse;
}
