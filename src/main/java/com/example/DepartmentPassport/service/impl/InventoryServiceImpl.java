package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.exceptions.CustomException;
import com.example.DepartmentPassport.model.dto.*;
import com.example.DepartmentPassport.model.dto.InventoryResponse;
import com.example.DepartmentPassport.model.entity.InventoryProfile;
import com.example.DepartmentPassport.model.entity.RoomProfile;
import com.example.DepartmentPassport.model.enums.inventory.InventoryStatus;
import com.example.DepartmentPassport.model.repositories.InventoryProfileRepo;
import com.example.DepartmentPassport.service.InventoryService;
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
public class InventoryServiceImpl implements InventoryService {
    private final InventoryProfileRepo inventoryProfileRepo;
    private final ObjectMapper mapper;
    private final RoomService roomService;


    @Override
    public InventoryResponse createInventory(InventoryRequest inventoryRequest) {
        InventoryProfile inventoryProfile = mapper.convertValue(inventoryRequest, InventoryProfile.class);
        inventoryProfile.setCreatedAt(LocalDateTime.now());
        inventoryProfile.setInventoryStatus(InventoryStatus.CREATED);

        InventoryProfile save = inventoryProfileRepo.save(inventoryProfile);
        return mapper.convertValue(save, InventoryResponse.class);
    }

    @Override
    public InventoryResponse getInventory(Long id) {
        InventoryProfile inventory = getInventoryById(id);
        return mapper.convertValue(inventory, InventoryResponse.class);
    }

    private InventoryProfile getInventoryById (Long id) {
        return inventoryProfileRepo.findById(id)
                .orElseThrow(() -> new CustomException("Inventory is not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public InventoryResponse updateInventory(Long id, InventoryRequest inventoryRequest) {
        InventoryProfile inventory = getInventoryById(id);

        inventory.setName(StringUtils.isBlank(inventoryRequest.getName()) ? inventory.getName() : inventoryRequest.getName());
        inventory.setInventoryNumber(StringUtils.isBlank(inventoryRequest.getInventoryNumber()) ? inventory.getInventoryNumber() : inventoryRequest.getInventoryNumber());
        inventory.setStartExploitation(inventoryRequest.getStartExploitation() == null ? inventory.getStartExploitation() : inventoryRequest.getStartExploitation());
        inventory.setLifeTime(inventoryRequest.getLifeTime() == null ? inventory.getLifeTime() : inventoryRequest.getLifeTime());

        inventory.setInventoryStatus(InventoryStatus.UPDATED);
        inventory.setUpdateAt(LocalDateTime.now());

        InventoryProfile save = inventoryProfileRepo.save(inventory);

        return mapper.convertValue(save, InventoryResponse.class);
    }

    @Override
    public void deleteInventory(Long id) {
        InventoryProfile Inventory = getInventoryById(id);
        Inventory.setInventoryStatus(InventoryStatus.DELETED);
        Inventory.setUpdateAt(LocalDateTime.now());

        inventoryProfileRepo.save(Inventory);
    }

    @Override
    public InventoryResponse addInventoryToRoom(Long inventoryId, Long roomId) {
        InventoryProfile inventoryProfile = getInventoryById(inventoryId);
        RoomProfile roomProfile = roomService.getRoomProfile(roomId);

        roomProfile.getInventoryProfiles().add(inventoryProfile);
        roomService.updateInventoryList(roomProfile);

        inventoryProfile.setRoomProfile(roomProfile);
        InventoryProfile save = inventoryProfileRepo.save(inventoryProfile);

        InventoryResponse inventoryResponse = mapper.convertValue(save, InventoryResponse.class);
        RoomResponse room = mapper.convertValue(roomProfile, RoomResponse.class);
        inventoryResponse.setRoomResponse(room);

        return inventoryResponse;
    }

    @Override
    public Page<InventoryResponse> getAllInventories(Integer page, Integer perPage, String sort, Sort.Direction order, String filter) {
        Pageable pageRequest = getPageRequest(page, perPage, sort, order);

        Page<InventoryProfile> pageList = inventoryProfileRepo.findAllNotDeleted(pageRequest);
        List<InventoryResponse> responses = pageList.getContent().stream()
                .map(c -> mapper.convertValue(c, InventoryResponse.class))
                .collect(Collectors.toList());
        return new PageImpl<>(responses);
    }
}
