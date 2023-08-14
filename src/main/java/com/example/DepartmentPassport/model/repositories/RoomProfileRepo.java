package com.example.DepartmentPassport.model.repositories;

import com.example.DepartmentPassport.model.entity.MedicalDeviceProfile;
import com.example.DepartmentPassport.model.entity.RoomProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomProfileRepo extends JpaRepository<RoomProfile, Long> {

    Page<RoomProfile> findAllNotDeleted(Pageable request);
}
