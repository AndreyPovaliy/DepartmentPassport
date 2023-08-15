package com.example.DepartmentPassport.model.repositories;


import com.example.DepartmentPassport.model.entity.DepartmentProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentProfileRepo extends JpaRepository<DepartmentProfile, Long> {

    @Query("select departmentProfile from DepartmentProfile departmentProfile where departmentProfile.departmentStatus <> '2'")
    Page<DepartmentProfile> findAllNotDeleted(Pageable request);
}
