package com.example.DepartmentPassport.service;

import com.example.DepartmentPassport.model.dto.InventoryRequest;
import com.example.DepartmentPassport.model.dto.InventoryResponse;

public interface InventoryService {
    InventoryResponse createInventory(InventoryRequest inventoryRequest);

    InventoryResponse getInventory (Long id);

    InventoryResponse updateInventory (Long id, InventoryRequest inventoryRequest);

    void deleteInventory (Long id);

//    Page<BuildingResponse> getAllBuilding (Integer page, Integer perPage, String sort, Sort.Direction order, String filter);
//
//    BuildingResponse addCarToUser(Long carId, Long userId);
}
