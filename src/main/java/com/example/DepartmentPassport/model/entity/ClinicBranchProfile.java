package com.example.DepartmentPassport.model.entity;


import com.example.DepartmentPassport.model.enums.clinicBranch.ClinicBranchStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "ClinicBranch")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClinicBranchProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "full_name")
    String fullName;
    @Column(name = "real_address")
    String realAddress;
    //    @ManyToOne
//    @JsonBackReference(value="driver_cars")
//    UserProfile userProfile;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "update_at")
    LocalDateTime updateAt;

    @Enumerated
    ClinicBranchStatus clinicBranchStatus;
}
