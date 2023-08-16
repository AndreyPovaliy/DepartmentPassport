package com.example.DepartmentPassport.service;

import com.example.DepartmentPassport.model.dto.BuildingRequest;
import com.example.DepartmentPassport.model.dto.BuildingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface BuildingService {

    BuildingResponse createBuilding (BuildingRequest buildingRequest);

    BuildingResponse getBuilding (Long id);

    BuildingResponse updateBuilding (Long id, BuildingRequest buildingRequest);

    void deleteBuilding (Long id);

    Page<BuildingResponse> getAllBuildings(Integer page, Integer perPage, String sort, Sort.Direction order, String filter);

    BuildingResponse addBuildingToClinic(Long buildingId, Long clinicId);


}
