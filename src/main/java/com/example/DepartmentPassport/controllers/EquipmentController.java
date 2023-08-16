package com.example.DepartmentPassport.controllers;

import com.example.DepartmentPassport.model.dto.EquipmentResponse;
import com.example.DepartmentPassport.model.dto.EquipmentRequest;
import com.example.DepartmentPassport.service.EquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@Tag(name="equipment")
@RestController
@RequestMapping("/equipment")
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;


    @PostMapping
    @Operation (summary = "create Equipment")
    public EquipmentResponse createEquipment(@RequestBody EquipmentRequest equipmentRequest) {

        return equipmentService.createEquipment(equipmentRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get Equipment")
    public EquipmentResponse readEquipment(@PathVariable Long id) {

        return equipmentService.getEquipment(id);
    }

    @PutMapping("/{id}")
    @Operation (summary = "update Equipment")
    public EquipmentResponse updateEquipment(@PathVariable Long id, @RequestBody EquipmentRequest equipmentRequest) {
        return equipmentService.updateEquipment(id, equipmentRequest);
    }

    @DeleteMapping("/{id}")
    @Operation (summary = "delete Equipment")
    public void deleteEquipment(@PathVariable Long id) {

        equipmentService.deleteEquipment(id);
    }


    @GetMapping("/allEquipments")
    @Operation (summary = "get all equipment")
    public Page<EquipmentResponse> getAllEquipments (@RequestParam (defaultValue = "1") Integer page,
                                                   @RequestParam (defaultValue = "10") Integer perPage,
                                                   @RequestParam (defaultValue = "name") String sort,
                                                   @RequestParam (defaultValue = "ASC") Sort.Direction order,
                                                   @RequestParam (required = false) String filter
    ) {

        return equipmentService.getAllEquipments (page,perPage,sort,order,filter);
    }

    @PostMapping("/addEquipmentToDepartment/{equipmentId}/{departmentId}")
    @Operation (summary = "add equipment to department")
    public EquipmentResponse addEquipmentToDepartment (@PathVariable Long equipmentId, @PathVariable Long departmentId) {
        return equipmentService.addEquipmentToDepartment(equipmentId, departmentId);
    }


}
