package com.example.DepartmentPassport.model.dto;

import com.example.DepartmentPassport.model.enums.drug.ReleaseForm;
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
public class DrugRequest {


    String tradeName;
    String workingSubstance;
    ReleaseForm releaseForm;
    Integer packingVolume;
    String measure;
    Date productionDate;
    Date bestBeforeDate;



}
