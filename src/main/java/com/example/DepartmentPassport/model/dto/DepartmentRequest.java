package com.example.DepartmentPassport.model.dto;

import com.example.DepartmentPassport.model.enums.department.AssistanceConditions;
import com.example.DepartmentPassport.model.enums.department.AssistanceForm;
import com.example.DepartmentPassport.model.enums.department.AssistanceProfile;
import com.example.DepartmentPassport.model.enums.department.DepartmentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentRequest {
    String name;
    String phone;
    DepartmentType departmentType;
    AssistanceProfile assistanceProfile;
    AssistanceConditions assistanceConditions;
    AssistanceForm assistanceForm;
    Integer numberPositionsDoctors;
    Integer numberPositionsNurseStaff;
    Integer numberPositionsJuniorMedicalStaff;
    Integer numberInpatientBeds;
    Integer numberOutpatientBeds;
}
