package com.example.DepartmentPassport.model.dto;

import com.example.DepartmentPassport.model.enums.clinicHR.Category;
import com.example.DepartmentPassport.model.enums.clinicHR.ClinicHrStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClinicHrRequest {

    ClinicHrStatus clinicHrStatus;

    String firstName;
    String lastName;
    String middleName;
    String jobTitle;
    Integer experience;
    Category category;
    Boolean isAccreditation;
    Date dateAccreditation;

//	- Отделение


}
