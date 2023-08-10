package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.model.dto.RoomRequest;
import com.example.DepartmentPassport.model.dto.RoomResponse;
import com.example.DepartmentPassport.model.entity.ClinicProfile;
import com.example.DepartmentPassport.model.entity.RoomProfile;
import com.example.DepartmentPassport.model.enums.clinic.ClinicStatus;
import com.example.DepartmentPassport.model.enums.room.RoomStatus;
import com.example.DepartmentPassport.model.repositories.RoomProfileRepo;
import com.example.DepartmentPassport.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomProfileRepo roomProfileRepo;
    private final ObjectMapper mapper;
    @Override
    public RoomResponse roomResponse(RoomRequest roomRequest) {
        return null;
    }

    @Override
    public RoomResponse createRoom(RoomRequest roomRequest) {
        return null;
    }

    @Override
    public RoomResponse getRoom(Long id) {
        return null;
    }

    @Override
    public RoomResponse updateRoom(Long id, RoomRequest roomRequest) {
        return null;
    }

    @Override
    public void deleteRoom(Long id) {
        RoomProfile room = getRoomById(id);
        room.setRoomStatus(RoomStatus.DELETED);
        room.setUpdateAt(LocalDateTime.now());

        roomProfileRepo.save(room);

    }
}
