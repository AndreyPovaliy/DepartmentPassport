package com.example.DepartmentPassport.controllers;


import com.example.DepartmentPassport.model.dto.BuildingRequest;
import com.example.DepartmentPassport.model.dto.BuildingResponse;
import com.example.DepartmentPassport.service.BuildingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@Tag(name="building")
@RestController
@RequestMapping("/building")
@RequiredArgsConstructor
public class BuildingController {
    private final BuildingService buildingService;


    @PostMapping
    @Operation(summary = "create Building")
    public BuildingResponse createBuilding(@RequestBody BuildingRequest buildingRequest) {

        return buildingService.createBuilding(buildingRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get Building")
    public BuildingResponse readBuilding(@PathVariable Long id) {

        return buildingService.getBuilding(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update Building")
    public BuildingResponse updateBuilding(@PathVariable Long id, @RequestBody BuildingRequest buildingRequest) {
        return buildingService.updateBuilding(id, buildingRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete Building")
    public void deleteBuilding(@PathVariable Long id) {

        buildingService.deleteBuilding(id);
    }

    @GetMapping("/allBuilding")
    @Operation(summary = "get all building")
    public Page<BuildingResponse> getAllBuilding(@RequestParam(defaultValue = "1") Integer page,
                                                @RequestParam(defaultValue = "10") Integer perPage,
                                                @RequestParam(defaultValue = "typeConstruction") String sort,
                                                @RequestParam(defaultValue = "ASC") Sort.Direction order,
                                                @RequestParam(required = false) String filter
    ) {

        return buildingService.getAllBuildings(page, perPage, sort, order, filter);
    }

    @PostMapping("/addBuildingToClinic/{buildingId}/{clinicId}")
    @Operation(summary = "add building to clinic")
    public BuildingResponse addBuildingToClinic(@PathVariable Long buildingId, @PathVariable Long clinicId) {
        return buildingService.addBuildingToClinic(buildingId, clinicId);
    }
}
