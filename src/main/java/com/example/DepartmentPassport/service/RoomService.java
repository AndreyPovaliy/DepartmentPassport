package com.example.DepartmentPassport.service;


import com.example.DepartmentPassport.model.dto.RoomResponse;
import com.example.DepartmentPassport.model.dto.RoomRequest;
import com.example.DepartmentPassport.model.dto.RoomResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface RoomService {

    RoomResponse createRoom(RoomRequest roomRequest);

    RoomResponse getRoom (Long id);

    RoomResponse updateRoom (Long id, RoomRequest roomRequest);

    void deleteRoom (Long id);

    RoomResponse addRoomToDepartment(Long adminHrId, Long departmentId);

    Page<RoomResponse> getAllRooms(Integer page, Integer perPage, String sort, Sort.Direction order, String filter);

}
