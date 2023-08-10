package com.example.DepartmentPassport.model.entity;

import com.example.DepartmentPassport.model.enums.building.BuildingStatus;
import com.example.DepartmentPassport.model.enums.department.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Department")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String phone;
    @Column(name = "department_type")
    DepartmentType departmentType;
    @Column(name = "assistance_profile")
    AssistanceProfile assistanceProfile;
    @Column(name = "assistance_conditions")
    AssistanceConditions assistanceConditions;
    @Column(name = "assistance_form")
    AssistanceForm assistanceForm;
    @Column(name = "number_positions_doctors")
    Integer numberPositionsDoctors;
    @Column(name = "number_positions_nurse_staff")
    Integer numberPositionsNurseStaff;
    @Column(name = "number_positions_junior_medical_staff")
    Integer numberPositionsJuniorMedicalStaff;
    @Column(name = "number_inpatient_beds")
    Integer numberInpatientBeds;
    @Column(name = "number_outpatient_beds")
    Integer numberOutpatientBeds;

    //    @ManyToOne
//    @JsonBackReference(value="driver_cars")
//    UserProfile userProfile;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "update_at")
    LocalDateTime updateAt;

    @Enumerated
    DepartmentStatus departmentStatus;
}
