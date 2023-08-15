package com.example.DepartmentPassport.model.entity;

import com.example.DepartmentPassport.model.enums.medicalDevice.MedicalDeviceStatus;
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
@Table(name = "MedicalDevice")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicalDeviceProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Column(name = "inventory_number")
    String inventoryNumber;
    @Column(name = "start_exploitation")
    Date startExploitation;
    @Column(name = "life_time")
    Integer lifeTime;
    @Column(name = "is_sterilization")
    Boolean isSterilization;
    @Column(name = "is_one_off")
    Boolean isOneOff;

    @ManyToOne
    @JsonBackReference(value="department_medicalDevice")
    DepartmentProfile departmentProfile;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "update_at")
    LocalDateTime updateAt;

    @Enumerated
    MedicalDeviceStatus medicalDeviceStatus;
}
