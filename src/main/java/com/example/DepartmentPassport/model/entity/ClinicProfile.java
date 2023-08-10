package com.example.DepartmentPassport.model.entity;

import com.example.DepartmentPassport.model.enums.building.BuildingStatus;
import com.example.DepartmentPassport.model.enums.clinic.ClinicStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    //    @ManyToOne
//    @JsonBackReference(value="driver_cars")
//    UserProfile userProfile;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "update_at")
    LocalDateTime updateAt;

    @Enumerated
    ClinicStatus clinicStatus;
}
