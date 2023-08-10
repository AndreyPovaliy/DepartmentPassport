package com.example.DepartmentPassport.controllers;

import com.example.DepartmentPassport.model.dto.EquipmentRequest;
import com.example.DepartmentPassport.model.dto.EquipmentResponse;
import com.example.DepartmentPassport.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//@Tag(name="ClinicHr")
@RestController
@RequestMapping("/ClinicHr")
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;


    @PostMapping
//    @Operation (summary = "create Equipment")
    public EquipmentResponse createEquipment(@RequestBody EquipmentRequest equipmentRequest) {

        return equipmentService.createEquipment(equipmentRequest);
    }

    @GetMapping("/{id}")
//    @Operation (summary = "get Equipment")
    public EquipmentResponse readEquipment(@PathVariable Long id) {

        return equipmentService.getEquipment(id);
    }

    @PutMapping("/{id}")
//    @Operation (summary = "update Equipment")
    public EquipmentResponse updateEquipment(@PathVariable Long id, @RequestBody EquipmentRequest equipmentRequest) {
        return equipmentService.updateEquipment(id, equipmentRequest);
    }

    @DeleteMapping
//    @Operation (summary = "delete Equipment")
    public void deleteEquipment(@PathVariable Long id) {

        equipmentService.deleteEquipment(id);
    }
}
