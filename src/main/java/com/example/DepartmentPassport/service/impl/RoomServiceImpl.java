package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.exceptions.CustomException;
import com.example.DepartmentPassport.model.dto.RoomRequest;
import com.example.DepartmentPassport.model.dto.RoomResponse;
import com.example.DepartmentPassport.model.entity.RoomProfile;
import com.example.DepartmentPassport.model.enums.room.RoomStatus;
import com.example.DepartmentPassport.model.repositories.RoomProfileRepo;
import com.example.DepartmentPassport.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomProfileRepo roomProfileRepo;
    private final ObjectMapper mapper;


    @Override
    public RoomResponse createRoom(RoomRequest roomRequest) {
        RoomProfile roomProfile = mapper.convertValue(roomRequest, RoomProfile.class);
        roomProfile.setCreatedAt(LocalDateTime.now());
        roomProfile.setRoomStatus(RoomStatus.CREATED);

        RoomProfile save = roomProfileRepo.save(roomProfile);
        return mapper.convertValue(save, RoomResponse.class);
    }

    @Override
    public RoomResponse getRoom(Long id) {
        RoomProfile room = getRoomById(id);
        return mapper.convertValue(room, RoomResponse.class);
    }

    private RoomProfile getRoomById (Long id) {
            return roomProfileRepo.findById(id)
                    .orElseThrow(() -> new CustomException("Room is not found", HttpStatus.NOT_FOUND));
        }



    @Override
    public RoomResponse updateRoom(Long id, RoomRequest roomRequest) {
        RoomProfile room = getRoomById(id);

        room.setName(StringUtils.isBlank(roomRequest.getName()) ? room.getName() : roomRequest.getName());
        room.setTypeRoom(roomRequest.getTypeRoom() == null ? room.getTypeRoom() : roomRequest.getTypeRoom());
        room.setArea(roomRequest.getArea() == null ? room.getArea() : roomRequest.getArea());

        room.setRoomStatus(RoomStatus.UPDATED);
        room.setUpdateAt(LocalDateTime.now());

        RoomProfile save = roomProfileRepo.save(room);

        return mapper.convertValue(save, RoomResponse.class);
    }

    @Override
    public void deleteRoom(Long id) {
        RoomProfile room = getRoomById(id);
        room.setRoomStatus(RoomStatus.DELETED);
        room.setUpdateAt(LocalDateTime.now());

        roomProfileRepo.save(room);

    }
}
