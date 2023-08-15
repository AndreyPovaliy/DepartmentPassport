package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.exceptions.CustomException;
import com.example.DepartmentPassport.model.dto.DepartmentResponse;
import com.example.DepartmentPassport.model.dto.RoomResponse;
import com.example.DepartmentPassport.model.dto.RoomRequest;
import com.example.DepartmentPassport.model.entity.DepartmentProfile;
import com.example.DepartmentPassport.model.entity.InventoryProfile;
import com.example.DepartmentPassport.model.entity.RoomProfile;
import com.example.DepartmentPassport.model.enums.room.RoomStatus;
import com.example.DepartmentPassport.model.repositories.RoomProfileRepo;
import com.example.DepartmentPassport.service.DepartmentService;
import com.example.DepartmentPassport.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.DepartmentPassport.utils.PaginationUtil.getPageRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomProfileRepo roomProfileRepo;
    private final ObjectMapper mapper;
    private final DepartmentService departmentService;


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

    @Override
    public RoomProfile getRoomProfile(Long id) {
        return roomProfileRepo.findById(id)
                .orElseThrow(() -> new CustomException("Room is not found", HttpStatus.NOT_FOUND));
    }

    public void updateInventoryList(RoomProfile roomProfile) {
        roomProfileRepo.save(roomProfile);
    }



    @Override
    public RoomResponse addRoomToDepartment(Long roomId, Long departmentId) {
        RoomProfile roomProfile = getRoomById(roomId);
        DepartmentProfile departmentProfile = departmentService.getDepartmentProfile(departmentId);

        departmentProfile.getRoomProfiles().add(roomProfile);
        departmentService.updateRoomList(departmentProfile);

        roomProfile.setDepartmentProfile(departmentProfile);
        RoomProfile save = roomProfileRepo.save(roomProfile);

        RoomResponse roomResponse = mapper.convertValue(save, RoomResponse.class);
        DepartmentResponse department = mapper.convertValue(departmentProfile, DepartmentResponse.class);
        roomResponse.setDepartmentResponse(department);

        return roomResponse;
    }

    @Override
    public Page<RoomResponse> getAllRooms(Integer page, Integer perPage, String sort, Sort.Direction order, String filter) {
        Pageable pageRequest = getPageRequest(page, perPage, sort, order);

        Page<RoomProfile> pageList = roomProfileRepo.findAllNotDeleted(pageRequest);
        List<RoomResponse> responses = pageList.getContent().stream()
                .map(c -> mapper.convertValue(c, RoomResponse.class))
                .collect(Collectors.toList());
        return new PageImpl<>(responses);
    }
}
