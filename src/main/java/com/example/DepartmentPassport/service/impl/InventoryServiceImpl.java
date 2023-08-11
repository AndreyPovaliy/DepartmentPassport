package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.exceptions.CustomException;
import com.example.DepartmentPassport.model.dto.InventoryRequest;
import com.example.DepartmentPassport.model.dto.InventoryResponse;
import com.example.DepartmentPassport.model.entity.InventoryProfile;
import com.example.DepartmentPassport.model.enums.inventory.InventoryStatus;
import com.example.DepartmentPassport.model.repositories.InventoryProfileRepo;
import com.example.DepartmentPassport.service.InventoryService;
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
public class InventoryServiceImpl implements InventoryService {
    private final InventoryProfileRepo inventoryProfileRepo;
    private final ObjectMapper mapper;


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
}
