package com.example.DepartmentPassport.model.entity;

import com.example.DepartmentPassport.model.enums.clinic.ClinicStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Clinic")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClinicProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "full_name")
    String fullName;
    @Column(name = "legal_address")
    String legalAddress;
    String director;
    String webSite;
    String phone;

    @OneToMany
    @JsonManagedReference(value="clinic_branches")
    List <ClinicBranchProfile> clinicBranchProfileList;

    @OneToMany
    @JsonManagedReference (value="clinic_buildings")
    List<BuildingProfile> buildingProfiles;


    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "update_at")
    LocalDateTime updateAt;

    @Enumerated
    ClinicStatus clinicStatus;
}
