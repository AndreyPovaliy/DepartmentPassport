package com.example.DepartmentPassport.model.repositories;


import com.example.DepartmentPassport.model.entity.BuildingProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingProfileRepo extends JpaRepository<BuildingProfile, Long> {
}
