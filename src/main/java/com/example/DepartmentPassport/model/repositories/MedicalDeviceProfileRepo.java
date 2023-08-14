package com.example.DepartmentPassport.model.repositories;

import com.example.DepartmentPassport.model.entity.EquipmentProfile;
import com.example.DepartmentPassport.model.entity.MedicalDeviceProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalDeviceProfileRepo extends JpaRepository<MedicalDeviceProfile, Long> {
    Page<MedicalDeviceProfile> findAllNotDeleted(Pageable request);
}
