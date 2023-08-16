package com.example.DepartmentPassport.controllers;

import com.example.DepartmentPassport.model.dto.InventoryRequest;
import com.example.DepartmentPassport.model.dto.InventoryResponse;
import com.example.DepartmentPassport.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@Tag(name="inventory")
@RestController
@RequestMapping("/inventory")
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

    @DeleteMapping("/{id}")
    @Operation (summary = "delete Inventory")
    public void deleteInventory(@PathVariable Long id) {

        inventoryService.deleteInventory(id);
    }

    @GetMapping("/allInventories")
    @Operation (summary = "get all inventories")
    public Page<InventoryResponse> getAllInventories (@RequestParam (defaultValue = "1") Integer page,
                                                     @RequestParam (defaultValue = "10") Integer perPage,
                                                     @RequestParam (defaultValue = "name") String sort,
                                                     @RequestParam (defaultValue = "ASC") Sort.Direction order,
                                                     @RequestParam (required = false) String filter
    ) {

        return inventoryService.getAllInventories (page,perPage,sort,order,filter);
    }

    @PostMapping("/addInventoryToRoom/{inventoryId}/{roomId}")
    @Operation (summary = "add inventory to room")
    public InventoryResponse addInventoryToRoom (@PathVariable Long inventoryId, @PathVariable Long roomId) {
        return inventoryService.addInventoryToRoom(inventoryId, roomId);
    }
}
