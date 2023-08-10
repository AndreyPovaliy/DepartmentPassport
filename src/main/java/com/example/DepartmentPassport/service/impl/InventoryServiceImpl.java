package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.model.dto.InventoryRequest;
import com.example.DepartmentPassport.model.dto.InventoryResponse;
import com.example.DepartmentPassport.model.repositories.InventoryProfileRepo;
import com.example.DepartmentPassport.service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryProfileRepo inventoryProfileRepo;
    private final ObjectMapper mapper;
    @Override
    public InventoryResponse createInventory(InventoryRequest inventoryRequest) {
        return null;
    }

    @Override
    public InventoryResponse getInventory(Long id) {
        return null;
    }

    @Override
    public InventoryResponse updateInventory(Long id, InventoryRequest inventoryRequest) {
        return null;
    }

    @Override
    public void deleteInventory(Long id) {

    }
}
