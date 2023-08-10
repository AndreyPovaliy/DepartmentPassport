package com.example.DepartmentPassport.model.repositories;

import com.example.DepartmentPassport.model.entity.ClinicProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ClinicProfileRepo extends JpaRepository <ClinicProfile, Long> {
}
