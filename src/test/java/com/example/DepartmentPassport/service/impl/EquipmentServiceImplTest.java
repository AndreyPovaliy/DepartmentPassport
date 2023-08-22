package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.model.dto.EquipmentRequest;
import com.example.DepartmentPassport.model.dto.EquipmentResponse;
import com.example.DepartmentPassport.model.entity.EquipmentProfile;
import com.example.DepartmentPassport.model.enums.equipment.EquipmentStatus;
import com.example.DepartmentPassport.model.repositories.EquipmentProfileRepo;
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
public class EquipmentServiceImplTest {
    @InjectMocks
    private EquipmentServiceImpl equipmentService;
    @Mock
    private EquipmentProfileRepo equipmentProfileRepo;
    @Spy
    private ObjectMapper mapper;

    @Test
    public void createEquipment() {
        EquipmentRequest equipmentRequest = new EquipmentRequest();
        equipmentRequest.setName("Рентген аппарат С-дуга");

        EquipmentProfile equipmentProfile = new EquipmentProfile();
        equipmentProfile.setId(1L);

        when(equipmentProfileRepo.save(any(EquipmentProfile.class))).thenReturn(equipmentProfile);

        EquipmentResponse result = equipmentService.createEquipment(equipmentRequest);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
    }

    @Test
    public void getEquipment() {
        EquipmentProfile equipmentProfile = new EquipmentProfile();
        equipmentProfile.setId(1L);

        when(equipmentProfileRepo.findById(1L)).thenReturn(Optional.of(equipmentProfile));

        EquipmentResponse result = equipmentService.getEquipment(1L);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
        assertEquals(equipmentProfile.getId(), result.getId());
    }

    @Test
    public void updateEquipment() {
        EquipmentProfile EquipmentProfile = new EquipmentProfile();
        EquipmentProfile.setId(1L);

        when (equipmentProfileRepo.findById(1L)).thenReturn(Optional.of(EquipmentProfile));

        EquipmentRequest equipmentRequest = new EquipmentRequest();
        equipmentService.updateEquipment(1L, equipmentRequest);
        verify(equipmentProfileRepo, times(1)).save(any(EquipmentProfile.class));
        assertEquals(EquipmentStatus.UPDATED, EquipmentProfile.getEquipmentStatus());
    }

    @Test
    public void deleteEquipment() {
        EquipmentProfile equipmentProfile = new EquipmentProfile();
        equipmentProfile.setId(1L);

        when (equipmentProfileRepo.findById(1L)).thenReturn(Optional.of(equipmentProfile));

        equipmentService.deleteEquipment(1L);
        verify(equipmentProfileRepo, times(1)).save(any(EquipmentProfile.class));
        assertEquals(EquipmentStatus.DELETED, equipmentProfile.getEquipmentStatus());
    }
}