package com.example.DepartmentPassport.service;


import com.example.DepartmentPassport.model.dto.RoomRequest;
import com.example.DepartmentPassport.model.dto.RoomResponse;

public interface RoomService {

    RoomResponse createRoom(RoomRequest roomRequest);

    RoomResponse getRoom (Long id);

    RoomResponse updateRoom (Long id, RoomRequest roomRequest);

    void deleteRoom (Long id);

//    Page<BuildingResponse> getAllBuilding (Integer page, Integer perPage, String sort, Sort.Direction order, String filter);
//
//    BuildingResponse addCarToUser(Long carId, Long userId);
}
