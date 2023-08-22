package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.model.dto.ClinicBranchRequest;
import com.example.DepartmentPassport.model.dto.ClinicBranchResponse;
import com.example.DepartmentPassport.model.entity.ClinicBranchProfile;
import com.example.DepartmentPassport.model.enums.clinicBranch.ClinicBranchStatus;
import com.example.DepartmentPassport.model.repositories.ClinicBranchProfileRepo;
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
public class ClinicBranchServiceImplTest {
    @InjectMocks
    private ClinicBranchServiceImpl clinicBranchService;
    @Mock
    private ClinicBranchProfileRepo clinicBranchProfileRepo;
    @Spy
    private ObjectMapper mapper;

    @Test
    public void createClinicBranch() {
        ClinicBranchRequest clinicBranchRequest = new ClinicBranchRequest();
        clinicBranchRequest.setFullName("Лечебно-диагностический комплекс (Кадетская)");

        ClinicBranchProfile clinicBranchProfile = new ClinicBranchProfile();
        clinicBranchProfile.setId(1L);

        when(clinicBranchProfileRepo.save(any(ClinicBranchProfile.class))).thenReturn(clinicBranchProfile);

        ClinicBranchResponse result = clinicBranchService.createClinicBranch(clinicBranchRequest);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
    }

    @Test
    public void getClinicBranch() {
        ClinicBranchProfile clinicBranchProfile = new ClinicBranchProfile();
        clinicBranchProfile.setId(1L);

        when(clinicBranchProfileRepo.findById(1L)).thenReturn(Optional.of(clinicBranchProfile));

        ClinicBranchResponse result = clinicBranchService.getClinicBranch(1L);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
        assertEquals(clinicBranchProfile.getId(), result.getId());
    }

    @Test
    public void updateClinicBranch() {
        ClinicBranchProfile clinicBranchProfile = new ClinicBranchProfile();
        clinicBranchProfile.setId(1L);

        when (clinicBranchProfileRepo.findById(1L)).thenReturn(Optional.of(clinicBranchProfile));

        ClinicBranchRequest clinicBranchRequest = new ClinicBranchRequest();
        clinicBranchService.updateClinicBranch(1L, clinicBranchRequest);
        verify(clinicBranchProfileRepo, times(1)).save(any(ClinicBranchProfile.class));
        assertEquals(ClinicBranchStatus.UPDATED, clinicBranchProfile.getClinicBranchStatus());
    }

    @Test
    public void deleteClinicBranch() {
        ClinicBranchProfile clinicBranchProfile = new ClinicBranchProfile();
        clinicBranchProfile.setId(1L);

        when (clinicBranchProfileRepo.findById(1L)).thenReturn(Optional.of(clinicBranchProfile));

        clinicBranchService.deleteClinicBranch(1L);
        verify(clinicBranchProfileRepo, times(1)).save(any(ClinicBranchProfile.class));
        assertEquals(ClinicBranchStatus.DELETED, clinicBranchProfile.getClinicBranchStatus());
    }
}