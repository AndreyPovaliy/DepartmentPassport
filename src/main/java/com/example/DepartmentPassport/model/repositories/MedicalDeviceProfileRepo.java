package com.example.DepartmentPassport.model.repositories;


import com.example.DepartmentPassport.model.entity.MedicalDeviceProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicalDeviceProfileRepo extends JpaRepository<MedicalDeviceProfile, Long> {
    @Query("select medicalDeviceProfile from MedicalDeviceProfile medicalDeviceProfile where medicalDeviceProfile.medicalDeviceStatus <> '2'")
    Page<MedicalDeviceProfile> findAllNotDeleted(Pageable request);
}
