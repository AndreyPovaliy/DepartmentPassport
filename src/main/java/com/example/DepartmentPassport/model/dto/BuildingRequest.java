package com.example.DepartmentPassport.model.dto;

import com.example.DepartmentPassport.model.enums.building.*;
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
public class BuildingRequest {
    TypeConstruction typeConstruction;
    Date yearLastOverhaul;
    Boolean isComplianceInteriorDecoration;
    HotWaterSupply hotWaterSupply;
    Heating heating;
    Sewerage sewerage;
    GasSupply gasSupply;
    Ventilation ventilation;
    PowerSupply powerSupply;
    TelephoneConnection telephoneConnection;
}
