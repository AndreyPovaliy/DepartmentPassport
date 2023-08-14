package com.example.DepartmentPassport.model.repositories;

import com.example.DepartmentPassport.model.entity.ClinicHrProfile;
import com.example.DepartmentPassport.model.entity.EquipmentProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentProfileRepo extends JpaRepository<EquipmentProfile, Long> {
    Page<EquipmentProfile> findAllNotDeleted(Pageable request);
}
