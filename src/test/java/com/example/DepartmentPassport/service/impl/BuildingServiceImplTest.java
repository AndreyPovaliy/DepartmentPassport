package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.model.dto.BuildingRequest;
import com.example.DepartmentPassport.model.dto.BuildingResponse;
import com.example.DepartmentPassport.model.entity.BuildingProfile;
import com.example.DepartmentPassport.model.enums.building.BuildingStatus;
import com.example.DepartmentPassport.model.repositories.BuildingProfileRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.example.DepartmentPassport.model.enums.building.GasSupply.CENTRALIZED;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BuildingServiceImplTest {
    @InjectMocks
    private BuildingServiceImpl buildingService;
    @Mock
    private BuildingProfileRepo buildingProfileRepo;
    @Spy
    private ObjectMapper mapper;

    @Test
    public void createBuilding() {
        BuildingRequest buildingRequest = new BuildingRequest();
        buildingRequest.setGasSupply(CENTRALIZED);

        BuildingProfile buildingProfile = new BuildingProfile();
        buildingProfile.setId(1L);

        when(buildingProfileRepo.save(any(BuildingProfile.class))).thenReturn(buildingProfile);

        BuildingResponse result = buildingService.createBuilding(buildingRequest);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
    }

    @Test
    public void getBuilding() {
        BuildingProfile buildingProfile = new BuildingProfile();
        buildingProfile.setId(1L);

        when(buildingProfileRepo.findById(1L)).thenReturn(Optional.of(buildingProfile));

        BuildingResponse result = buildingService.getBuilding(1L);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
        assertEquals(buildingProfile.getId(), result.getId());
    }

    @Test
    public void updateBuilding() {
        BuildingProfile buildingProfile = new BuildingProfile();
        buildingProfile.setId(1L);

        when (buildingProfileRepo.findById(1L)).thenReturn(Optional.of(buildingProfile));

        BuildingRequest BuildingRequest = new BuildingRequest();
        buildingService.updateBuilding(1L, BuildingRequest);
        verify(buildingProfileRepo, times(1)).save(any(BuildingProfile.class));
        assertEquals(BuildingStatus.UPDATED, buildingProfile.getBuildingStatus());
    }

    @Test
    public void deleteBuilding() {
        BuildingProfile buildingProfile = new BuildingProfile();
        buildingProfile.setId(1L);

        when (buildingProfileRepo.findById(1L)).thenReturn(Optional.of(buildingProfile));

        buildingService.deleteBuilding(1L);
        verify(buildingProfileRepo, times(1)).save(any(BuildingProfile.class));
        assertEquals(BuildingStatus.DELETED, buildingProfile.getBuildingStatus());
    }
}