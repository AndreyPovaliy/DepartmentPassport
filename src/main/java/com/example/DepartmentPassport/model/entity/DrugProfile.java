package com.example.DepartmentPassport.model.entity;


import com.example.DepartmentPassport.model.enums.drug.DrugStatus;
import com.example.DepartmentPassport.model.enums.drug.ReleaseForm;
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
@Table(name = "Drug")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DrugProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "trade_name")
    String tradeName;
    @Column(name = "working_substance")
    String workingSubstance;
    @Column(name = "release_form")
    ReleaseForm releaseForm;
    @Column(name = "packing_volume")
    Integer packingVolume;
    String measure;
    @Column(name = "production_date")
    Date productionDate;
    @Column(name = "best_before_date")
    Date bestBeforeDate;

    @ManyToOne
    @JsonBackReference(value="department_drug")
    DepartmentProfile departmentProfile;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "update_at")
    LocalDateTime updateAt;

    @Enumerated
    DrugStatus drugStatus;
}
