package com.example.DepartmentPassport.service;

import com.example.DepartmentPassport.model.dto.InventoryRequest;
import com.example.DepartmentPassport.model.dto.InventoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface InventoryService {
    InventoryResponse createInventory(InventoryRequest inventoryRequest);

    InventoryResponse getInventory (Long id);

    InventoryResponse updateInventory (Long id, InventoryRequest inventoryRequest);

    void deleteInventory (Long id);


    InventoryResponse addInventoryToRoom(Long inventoryId, Long roomId);

    Page<InventoryResponse> getAllInventories(Integer page, Integer perPage, String sort, Sort.Direction order, String filter);
}
