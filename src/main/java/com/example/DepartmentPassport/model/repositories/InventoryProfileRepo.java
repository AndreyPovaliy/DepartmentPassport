package com.example.DepartmentPassport.model.repositories;

import com.example.DepartmentPassport.model.entity.InventoryProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryProfileRepo extends JpaRepository<InventoryProfile, Long> {
}
