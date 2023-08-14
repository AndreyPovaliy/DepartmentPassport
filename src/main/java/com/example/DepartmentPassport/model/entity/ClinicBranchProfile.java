package com.example.DepartmentPassport.model.entity;


import com.example.DepartmentPassport.model.enums.clinicBranch.ClinicBranchStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne
    @JsonBackReference(value="clinic_branches")
    ClinicProfile clinicProfile;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "update_at")
    LocalDateTime updateAt;

    @Enumerated
    ClinicBranchStatus clinicBranchStatus;

    @OneToMany
    @JsonManagedReference(value="branch_departments")
    List<DepartmentProfile> departmentProfiles;
}
