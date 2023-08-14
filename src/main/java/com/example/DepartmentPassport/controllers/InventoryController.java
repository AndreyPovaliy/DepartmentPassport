package com.example.DepartmentPassport.controllers;

import com.example.DepartmentPassport.model.dto.InventoryRequest;
import com.example.DepartmentPassport.model.dto.InventoryResponse;
import com.example.DepartmentPassport.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name="ClinicHr")
@RestController
@RequestMapping("/ClinicHr")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    @Operation (summary = "create Inventory")
    public InventoryResponse createInventory(@RequestBody InventoryRequest inventoryRequest) {

        return inventoryService.createInventory(inventoryRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get Inventory")
    public InventoryResponse readInventory(@PathVariable Long id) {

        return inventoryService.getInventory(id);
    }

    @PutMapping("/{id}")
    @Operation (summary = "update Inventory")
    public InventoryResponse updateInventory(@PathVariable Long id, @RequestBody InventoryRequest inventoryRequest) {
        return inventoryService.updateInventory(id, inventoryRequest);
    }

    @DeleteMapping
    @Operation (summary = "delete Inventory")
    public void deleteInventory(@PathVariable Long id) {

        inventoryService.deleteInventory(id);
    }
}
