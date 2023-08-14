package com.example.DepartmentPassport.model.repositories;

import com.example.DepartmentPassport.model.entity.AdminHrProfile;
import com.example.DepartmentPassport.model.entity.ClinicHrProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicHrProfileRepo extends JpaRepository<ClinicHrProfile, Long> {
    Page<ClinicHrProfile> findAllNotDeleted(Pageable request);
}
