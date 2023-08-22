package com.example.DepartmentPassport.model.repositories;

import com.example.DepartmentPassport.model.entity.ClinicProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  ClinicProfileRepo extends JpaRepository <ClinicProfile, Long> {

    Optional<ClinicProfile> findAllByDirector(String director);
}
