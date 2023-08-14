package com.example.DepartmentPassport.model.repositories;

import com.example.DepartmentPassport.model.entity.AdminHrProfile;
import com.example.DepartmentPassport.model.entity.DepartmentProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminHrProfileRepo extends JpaRepository <AdminHrProfile, Long> {
    Page<AdminHrProfile> findAllNotDeleted(Pageable request);

}
