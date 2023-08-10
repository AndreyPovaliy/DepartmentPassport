package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.model.dto.BuildingRequest;
import com.example.DepartmentPassport.model.dto.BuildingResponse;
import com.example.DepartmentPassport.model.repositories.BuildingProfileRepo;
import com.example.DepartmentPassport.service.BuildingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {
    private final BuildingProfileRepo buildingProfileRepo;
    private final ObjectMapper mapper;

    @Override
    public BuildingResponse createBuilding(BuildingRequest buildingRequest) {
        return null;
    }

    @Override
    public BuildingResponse getBuilding(Long id) {
        return null;
    }

    @Override
    public BuildingResponse updateBuilding(Long id, BuildingRequest buildingRequest) {
        return null;
    }

    @Override
    public void deleteBuilding(Long id) {

    }
}
