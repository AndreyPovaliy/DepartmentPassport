package com.example.DepartmentPassport.model.entity;


import com.example.DepartmentPassport.model.enums.clinicHR.Category;
import com.example.DepartmentPassport.model.enums.clinicHR.ClinicHrStatus;
import com.example.DepartmentPassport.model.enums.clinicHR.Position;
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
@Table(name = "ClinicHr")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClinicHrProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Position position;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "middle_name")
    String middleName;
    @Column(name = "job_title")
    String jobTitle;
    Integer experience;
    Category category;
    @Column(name = "is_accreditation")
    Boolean isAccreditation;
    @Column(name = "date_accreditation")
    Date dateAccreditation;

    @ManyToOne
    @JsonBackReference(value="department_clinicHr")
    DepartmentProfile departmentProfile;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "update_at")
    LocalDateTime updateAt;

    @Enumerated
    ClinicHrStatus clinicHrStatus;
}
