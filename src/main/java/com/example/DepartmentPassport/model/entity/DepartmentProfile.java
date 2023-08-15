package com.example.DepartmentPassport.model.entity;


import com.example.DepartmentPassport.model.enums.department.*;
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

    @ManyToOne
    @JsonBackReference(value="branch_departments")
    ClinicBranchProfile clinicBranchProfile;

    @OneToMany
    @JsonManagedReference(value="department_adminHr")
    List<AdminHrProfile> adminHrProfiles;

    @OneToMany
    @JsonManagedReference(value="department_clinicHr")
    List<ClinicHrProfile> clinicHrProfiles;

    @OneToMany
    @JsonManagedReference(value="department_equipment")
    List<EquipmentProfile> equipmentProfiles;

    @OneToMany
    @JsonManagedReference(value="department_medicalDevice")
    List<MedicalDeviceProfile> medicalDeviceProfiles;

    @OneToMany
    @JsonManagedReference(value="department_room")
    List<RoomProfile> roomProfiles;

    @OneToMany
    @JsonManagedReference(value="department_drug")
    List<DrugProfile> drugProfiles;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "update_at")
    LocalDateTime updateAt;

    @Enumerated
    DepartmentStatus departmentStatus;


}
