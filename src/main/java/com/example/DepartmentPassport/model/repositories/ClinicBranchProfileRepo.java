package com.example.DepartmentPassport.model.repositories;


import com.example.DepartmentPassport.model.entity.ClinicBranchProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClinicBranchProfileRepo extends JpaRepository<ClinicBranchProfile, Long> {
    @Query("select clinicBranchProfile from ClinicBranchProfile clinicBranchProfile where clinicBranchProfile.clinicBranchStatus <> '2'")
    Page<ClinicBranchProfile> findAllNotDeleted(Pageable request);
}
