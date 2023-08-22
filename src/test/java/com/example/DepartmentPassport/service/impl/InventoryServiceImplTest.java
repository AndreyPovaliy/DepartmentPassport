package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.model.dto.InventoryRequest;
import com.example.DepartmentPassport.model.dto.InventoryResponse;
import com.example.DepartmentPassport.model.entity.InventoryProfile;
import com.example.DepartmentPassport.model.enums.inventory.InventoryStatus;
import com.example.DepartmentPassport.model.repositories.InventoryProfileRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InventoryServiceImplTest {
    @InjectMocks
    private InventoryServiceImpl inventoryService;
    @Mock
    private InventoryProfileRepo inventoryProfileRepo;
    @Spy
    private ObjectMapper mapper;

    @Test
    public void createInventory() {
        InventoryRequest inventoryRequest = new InventoryRequest();
        inventoryRequest.setName("Кровать");

        InventoryProfile inventoryProfile = new InventoryProfile();
        inventoryProfile.setId(1L);

        when(inventoryProfileRepo.save(any(InventoryProfile.class))).thenReturn(inventoryProfile);

        InventoryResponse result = inventoryService.createInventory(inventoryRequest);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
    }

    @Test
    public void getInventory() {
        InventoryProfile inventoryProfile = new InventoryProfile();
        inventoryProfile.setId(1L);

        when(inventoryProfileRepo.findById(1L)).thenReturn(Optional.of(inventoryProfile));

        InventoryResponse result = inventoryService.getInventory(1L);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
        assertEquals(inventoryProfile.getId(), result.getId());
    }

    @Test
    public void updateInventory() {
        InventoryProfile inventoryProfile = new InventoryProfile();
        inventoryProfile.setId(1L);

        when (inventoryProfileRepo.findById(1L)).thenReturn(Optional.of(inventoryProfile));

        InventoryRequest inventoryRequest = new InventoryRequest();
        inventoryService.updateInventory(1L, inventoryRequest);
        verify(inventoryProfileRepo, times(1)).save(any(InventoryProfile.class));
        assertEquals(InventoryStatus.UPDATED, inventoryProfile.getInventoryStatus());
    }

    @Test
    public void deleteInventory() {
        InventoryProfile inventoryProfile = new InventoryProfile();
        inventoryProfile.setId(1L);

        when (inventoryProfileRepo.findById(1L)).thenReturn(Optional.of(inventoryProfile));

        inventoryService.deleteInventory(1L);
        verify(inventoryProfileRepo, times(1)).save(any(InventoryProfile.class));
        assertEquals(InventoryStatus.DELETED, inventoryProfile.getInventoryStatus());
    }
}