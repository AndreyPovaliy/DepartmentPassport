package com.example.DepartmentPassport.model.repositories;


import com.example.DepartmentPassport.model.entity.RoomProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomProfileRepo extends JpaRepository<RoomProfile, Long> {
    @Query("select roomProfile from RoomProfile roomProfile where roomProfile.roomStatus <> '2'")

    Page<RoomProfile> findAllNotDeleted(Pageable request);
}
