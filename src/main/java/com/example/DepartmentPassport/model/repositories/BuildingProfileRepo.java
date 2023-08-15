package com.example.DepartmentPassport.model.repositories;


import com.example.DepartmentPassport.model.entity.BuildingProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BuildingProfileRepo extends JpaRepository<BuildingProfile, Long> {

    @Query("select buildingProfile from BuildingProfile buildingProfile where buildingProfile.buildingStatus <> '2'")
    Page<BuildingProfile> findAllNotDeleted(Pageable request);
}
