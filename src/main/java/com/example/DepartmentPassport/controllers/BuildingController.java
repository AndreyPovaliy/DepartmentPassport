package com.example.DepartmentPassport.controllers;

import com.example.DepartmentPassport.model.dto.BuildingRequest;
import com.example.DepartmentPassport.model.dto.BuildingResponse;
import com.example.DepartmentPassport.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//@Tag(name="building")
@RestController
@RequestMapping("/building")
@RequiredArgsConstructor
public class BuildingController {
    private final BuildingService buildingService;


    @PostMapping
//    @Operation (summary = "create Building")
    public BuildingResponse createBuilding(@RequestBody BuildingRequest buildingRequest) {

        return buildingService.createBuilding(buildingRequest);
    }

    @GetMapping("/{id}")
//    @Operation (summary = "get Building")
    public BuildingResponse readBuilding(@PathVariable Long id) {

        return buildingService.getBuilding(id);
    }

    @PutMapping("/{id}")
//    @Operation (summary = "update Building")
    public BuildingResponse updateBuilding(@PathVariable Long id, @RequestBody BuildingRequest buildingRequest) {
        return buildingService.updateBuilding(id, buildingRequest);
    }

    @DeleteMapping
//    @Operation (summary = "delete Building")
    public void deleteBuilding(@PathVariable Long id) {

        buildingService.deleteBuilding(id);
    }
}
