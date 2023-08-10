package com.example.DepartmentPassport.service;

import com.example.DepartmentPassport.model.dto.BuildingRequest;
import com.example.DepartmentPassport.model.dto.BuildingResponse;

public interface BuildingService {

    BuildingResponse createBuilding (BuildingRequest buildingRequest);

    BuildingResponse getBuilding (Long id);

    BuildingResponse updateBuilding (Long id, BuildingRequest buildingRequest);

    void deleteBuilding (Long id);

//    Page<BuildingResponse> getAllBuilding (Integer page, Integer perPage, String sort, Sort.Direction order, String filter);
//
//    BuildingResponse addCarToUser(Long carId, Long userId);
}
