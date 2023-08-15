package com.example.DepartmentPassport.model.repositories;


import com.example.DepartmentPassport.model.entity.EquipmentProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EquipmentProfileRepo extends JpaRepository<EquipmentProfile, Long> {
    @Query("select equipmentProfile from EquipmentProfile equipmentProfile where equipmentProfile.equipmentStatus <> '2'")
    Page<EquipmentProfile> findAllNotDeleted(Pageable request);
}
