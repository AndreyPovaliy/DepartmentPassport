package com.example.DepartmentPassport.model.entity;

import com.example.DepartmentPassport.model.enums.adminHR.AdminHrStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name ="Administration_HR")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminHrProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column (name = "job_title")
    String jobTitle;
    @Column (name = "first_name")
    String firstName;
    @Column (name = "last_name")
    String lastName;
    @Column (name = "middle_name")
    String middleName;
    String phone;

    @ManyToOne
    @JsonBackReference(value="department_adminHr")
    DepartmentProfile departmentProfile;

    @Column (name = "created_at")
    LocalDateTime createdAt;

    @Column (name = "update_at")
    LocalDateTime updateAt;

    @Enumerated
    AdminHrStatus adminHrStatus;
}
