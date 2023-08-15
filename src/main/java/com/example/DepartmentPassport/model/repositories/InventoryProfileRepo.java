package com.example.DepartmentPassport.model.repositories;

import com.example.DepartmentPassport.model.entity.InventoryProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InventoryProfileRepo extends JpaRepository<InventoryProfile, Long> {
    @Query("select inventoryProfile from InventoryProfile inventoryProfile where inventoryProfile.inventoryStatus <> '2'")
    Page<InventoryProfile> findAllNotDeleted(Pageable request);
}
