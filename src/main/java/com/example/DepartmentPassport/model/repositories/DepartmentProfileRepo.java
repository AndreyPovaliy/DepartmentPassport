package com.example.DepartmentPassport.model.repositories;

import com.example.DepartmentPassport.model.entity.DepartmentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentProfileRepo extends JpaRepository<DepartmentProfile, Long> {
}
