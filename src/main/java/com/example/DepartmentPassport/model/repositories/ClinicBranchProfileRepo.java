package com.example.DepartmentPassport.model.repositories;


import com.example.DepartmentPassport.model.entity.ClinicBranchProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicBranchProfileRepo extends JpaRepository<ClinicBranchProfile, Long> {

    Page<ClinicBranchProfile> findAllNotDeleted(Pageable request);
}
