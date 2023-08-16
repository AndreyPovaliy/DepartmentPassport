package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.exceptions.CustomException;
import com.example.DepartmentPassport.model.dto.BuildingRequest;
import com.example.DepartmentPassport.model.dto.BuildingResponse;
import com.example.DepartmentPassport.model.dto.ClinicResponse;
import com.example.DepartmentPassport.model.entity.BuildingProfile;
import com.example.DepartmentPassport.model.entity.ClinicProfile;
import com.example.DepartmentPassport.model.enums.building.*;
import com.example.DepartmentPassport.model.repositories.BuildingProfileRepo;
import com.example.DepartmentPassport.service.BuildingService;
import com.example.DepartmentPassport.service.ClinicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.DepartmentPassport.utils.PaginationUtil.getPageRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {
    private final BuildingProfileRepo buildingProfileRepo;
    private final ObjectMapper mapper;
    private final ClinicService clinicService;


    @Override
    public BuildingResponse createBuilding(BuildingRequest buildingRequest) {
        BuildingProfile buildingProfile = mapper.convertValue(buildingRequest, BuildingProfile.class);
        buildingProfile.setCreatedAt(LocalDateTime.now());
        buildingProfile.setBuildingStatus(BuildingStatus.CREATED);

        BuildingProfile save = buildingProfileRepo.save(buildingProfile);
        return mapper.convertValue(save, BuildingResponse.class);
    }

    @Override
    public BuildingResponse getBuilding(Long id) {
        BuildingProfile building = getBuildingById(id);
        return mapper.convertValue(building, BuildingResponse.class);
    }

    private BuildingProfile getBuildingById(Long id) {
        return buildingProfileRepo.findById(id)
                .orElseThrow(() -> new CustomException("Building is not found", HttpStatus.NOT_FOUND));
    }


    @Override
    public BuildingResponse updateBuilding(Long id, BuildingRequest buildingRequest) {
        BuildingProfile building = getBuildingById(id);

        building.setTypeConstruction(buildingRequest.getTypeConstruction() == null ? building.getTypeConstruction() : buildingRequest.getTypeConstruction());
        building.setYearLastOverhaul(buildingRequest.getYearLastOverhaul() == null ? building.getYearLastOverhaul() : buildingRequest.getYearLastOverhaul());
        building.setIsComplianceInteriorDecoration(buildingRequest.getIsComplianceInteriorDecoration() == null ? building.getIsComplianceInteriorDecoration() : buildingRequest.getIsComplianceInteriorDecoration());
        building.setHotWaterSupply(buildingRequest.getHotWaterSupply() == null ? building.getHotWaterSupply() : buildingRequest.getHotWaterSupply());
        building.setHeating(buildingRequest.getHeating() == null ? building.getHeating() : buildingRequest.getHeating());
        building.setSewerage(buildingRequest.getSewerage() == null ? building.getSewerage() : buildingRequest.getSewerage());
        building.setGasSupply(buildingRequest.getGasSupply() == null ? building.getGasSupply() : buildingRequest.getGasSupply());
        building.setVentilation(buildingRequest.getVentilation() == null ? building.getVentilation() : buildingRequest.getVentilation());
        building.setPowerSupply(buildingRequest.getPowerSupply() == null ? building.getPowerSupply() : buildingRequest.getPowerSupply());
        building.setTelephoneConnection(buildingRequest.getTelephoneConnection() == null ? building.getTelephoneConnection() : buildingRequest.getTelephoneConnection());


        building.setBuildingStatus(BuildingStatus.UPDATED);
        building.setUpdateAt(LocalDateTime.now());

        BuildingProfile save = buildingProfileRepo.save(building);

        return mapper.convertValue(save, BuildingResponse.class);
    }

    @Override
    public void deleteBuilding(Long id) {
        BuildingProfile building = getBuildingById(id);
        building.setBuildingStatus(BuildingStatus.DELETED);
        building.setUpdateAt(LocalDateTime.now());

        buildingProfileRepo.save(building);
    }

    @Override
    public Page<BuildingResponse> getAllBuildings(Integer page, Integer perPage, String sort, Sort.Direction order, String filter) {

        Pageable pageRequest = getPageRequest(page, perPage, sort, order);

        Page<BuildingProfile> pageList = buildingProfileRepo.findAllNotDeleted(pageRequest);
        List<BuildingResponse> responses = pageList.getContent().stream()
                .map(c -> mapper.convertValue(c, BuildingResponse.class))
                .collect(Collectors.toList());
        return new PageImpl<>(responses);

    }



    @Override
    public BuildingResponse addBuildingToClinic(Long carId, Long userId) {
        BuildingProfile buildingProfile = getBuildingById(carId);
        ClinicProfile clinicProfile = clinicService.getClinicProfile(userId);

        clinicProfile.getBuildingProfiles().add(buildingProfile);
        clinicService.updateBuildingList(clinicProfile);

        buildingProfile.setClinicProfile(clinicProfile);
        BuildingProfile save = buildingProfileRepo.save(buildingProfile);

        BuildingResponse buildingResponse = mapper.convertValue(save, BuildingResponse.class);
        ClinicResponse clinicResponse = mapper.convertValue(clinicProfile, ClinicResponse.class);
        buildingResponse.setClinicResponse(clinicResponse);

        return buildingResponse;
    }


}
