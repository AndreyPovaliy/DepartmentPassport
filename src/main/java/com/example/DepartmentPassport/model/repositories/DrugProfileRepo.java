package com.example.DepartmentPassport.model.repositories;


import com.example.DepartmentPassport.model.entity.DrugProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DrugProfileRepo extends JpaRepository<DrugProfile, Long> {

    @Query("select clinicHrProfile from ClinicHrProfile clinicHrProfile where clinicHrProfile.clinicHrStatus <> '2'")
    Page<DrugProfile> findAllNotDeleted(Pageable request);
}
