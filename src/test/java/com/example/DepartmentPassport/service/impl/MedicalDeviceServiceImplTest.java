package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.model.dto.MedicalDeviceRequest;
import com.example.DepartmentPassport.model.dto.MedicalDeviceResponse;
import com.example.DepartmentPassport.model.entity.MedicalDeviceProfile;
import com.example.DepartmentPassport.model.enums.medicalDevice.MedicalDeviceStatus;
import com.example.DepartmentPassport.model.repositories.MedicalDeviceProfileRepo;
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
public class MedicalDeviceServiceImplTest {
    @InjectMocks
    private MedicalDeviceServiceImpl medicalDeviceService;
    @Mock
    private MedicalDeviceProfileRepo medicalDeviceProfileRepo;
    @Spy
    private ObjectMapper mapper;

    @Test
    public void createMedicalDevice() {
        MedicalDeviceRequest medicalDeviceRequest = new MedicalDeviceRequest();
        medicalDeviceRequest.setName("Каутер");

        MedicalDeviceProfile medicalDeviceProfile = new MedicalDeviceProfile();
        medicalDeviceProfile.setId(1L);

        when(medicalDeviceProfileRepo.save(any(MedicalDeviceProfile.class))).thenReturn(medicalDeviceProfile);

        MedicalDeviceResponse result = medicalDeviceService.createMedicalDevice(medicalDeviceRequest);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
    }

    @Test
    public void getMedicalDevice() {
        MedicalDeviceProfile medicalDeviceProfile = new MedicalDeviceProfile();
        medicalDeviceProfile.setId(1L);

        when(medicalDeviceProfileRepo.findById(1L)).thenReturn(Optional.of(medicalDeviceProfile));

        MedicalDeviceResponse result = medicalDeviceService.getMedicalDevice(1L);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
        assertEquals(medicalDeviceProfile.getId(), result.getId());
    }

    @Test
    public void updateMedicalDevice() {
        MedicalDeviceProfile medicalDeviceProfile = new MedicalDeviceProfile();
        medicalDeviceProfile.setId(1L);

        when (medicalDeviceProfileRepo.findById(1L)).thenReturn(Optional.of(medicalDeviceProfile));

        MedicalDeviceRequest medicalDeviceRequest = new MedicalDeviceRequest();
        medicalDeviceService.updateMedicalDevice(1L, medicalDeviceRequest);
        verify(medicalDeviceProfileRepo, times(1)).save(any(MedicalDeviceProfile.class));
        assertEquals(MedicalDeviceStatus.UPDATED, medicalDeviceProfile.getMedicalDeviceStatus());
    }

    @Test
    public void deleteMedicalDevice() {
        MedicalDeviceProfile medicalDeviceProfile = new MedicalDeviceProfile();
        medicalDeviceProfile.setId(1L);

        when (medicalDeviceProfileRepo.findById(1L)).thenReturn(Optional.of(medicalDeviceProfile));

        medicalDeviceService.deleteMedicalDevice(1L);
        verify(medicalDeviceProfileRepo, times(1)).save(any(MedicalDeviceProfile.class));
        assertEquals(MedicalDeviceStatus.DELETED, medicalDeviceProfile.getMedicalDeviceStatus());
    }
}