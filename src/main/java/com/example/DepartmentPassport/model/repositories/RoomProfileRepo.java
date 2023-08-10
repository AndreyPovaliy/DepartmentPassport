package com.example.DepartmentPassport.model.repositories;

import com.example.DepartmentPassport.model.entity.RoomProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomProfileRepo extends JpaRepository<RoomProfile, Long> {
}
