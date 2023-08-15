package com.example.DepartmentPassport.model.repositories;

import com.example.DepartmentPassport.model.entity.AdminHrProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminHrProfileRepo extends JpaRepository <AdminHrProfile, Long> {
    @Query("select adminHrProfile from AdminHrProfile adminHrProfile where adminHrProfile.adminHrStatus <> '2'")
    Page<AdminHrProfile> findAllNotDeleted(Pageable request);

}
