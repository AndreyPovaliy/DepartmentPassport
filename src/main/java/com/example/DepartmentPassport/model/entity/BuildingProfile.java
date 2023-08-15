package com.example.DepartmentPassport.model.entity;

import com.example.DepartmentPassport.model.enums.building.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Building")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuildingProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "type_construction")
    TypeConstruction typeConstruction;
    @Column(name = "year_last_overhaul")
    Date yearLastOverhaul;
    @Column(name = "is_compliance_interior_decoration")
    Boolean isComplianceInteriorDecoration;
    @Column(name = "hot_water_supply")
    HotWaterSupply hotWaterSupply;
    Heating heating;
    Sewerage sewerage;
    @Column(name = "gas_supply")
    GasSupply gasSupply;
    Ventilation ventilation;
    @Column(name = "power_supply")
    PowerSupply powerSupply;
    @Column(name = "telephone_connection")
    TelephoneConnection telephoneConnection;

    @ManyToOne
    @JsonBackReference(value="clinic_buildings")
    ClinicProfile clinicProfile;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "update_at")
    LocalDateTime updateAt;

    @Enumerated
    BuildingStatus buildingStatus;
}
