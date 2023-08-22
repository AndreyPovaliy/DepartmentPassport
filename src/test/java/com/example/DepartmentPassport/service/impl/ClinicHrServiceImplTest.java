package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.model.dto.ClinicHrRequest;
import com.example.DepartmentPassport.model.dto.ClinicHrResponse;
import com.example.DepartmentPassport.model.entity.ClinicHrProfile;
import com.example.DepartmentPassport.model.enums.clinicHR.ClinicHrStatus;
import com.example.DepartmentPassport.model.repositories.ClinicHrProfileRepo;
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
public class ClinicHrServiceImplTest {
    @InjectMocks
    private ClinicHrServiceImpl clinicHrService;
    @Mock
    private ClinicHrProfileRepo clinicHrProfileRepo;
    @Spy
    private ObjectMapper mapper;

    @Test
    public void createClinicHrHr() {
        ClinicHrRequest clinicHrRequest = new ClinicHrRequest();
        clinicHrRequest.setFirstName("Алексей");

        ClinicHrProfile clinicHrProfile = new ClinicHrProfile();
        clinicHrProfile.setId(1L);

        when(clinicHrProfileRepo.save(any(ClinicHrProfile.class))).thenReturn(clinicHrProfile);

        ClinicHrResponse result = clinicHrService.createClinicHr(clinicHrRequest);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
    }

    @Test
    public void getClinicHrHr() {
        ClinicHrProfile clinicHrProfile = new ClinicHrProfile();
        clinicHrProfile.setId(1L);

        when(clinicHrProfileRepo.findById(1L)).thenReturn(Optional.of(clinicHrProfile));

        ClinicHrResponse result = clinicHrService.getClinicHr(1L);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
        assertEquals(clinicHrProfile.getId(), result.getId());
    }

    @Test
    public void updateClinicHrHr() {
        ClinicHrProfile clinicHrProfile = new ClinicHrProfile();
        clinicHrProfile.setId(1L);

        when (clinicHrProfileRepo.findById(1L)).thenReturn(Optional.of(clinicHrProfile));

        ClinicHrRequest clinicHrRequest = new ClinicHrRequest();
        clinicHrService.updateClinicHr(1L, clinicHrRequest);
        verify(clinicHrProfileRepo, times(1)).save(any(ClinicHrProfile.class));
        assertEquals(ClinicHrStatus.UPDATED, clinicHrProfile.getClinicHrStatus());
    }

    @Test
    public void deleteClinicHrHr() {
        ClinicHrProfile clinicHrProfile = new ClinicHrProfile();
        clinicHrProfile.setId(1L);

        when (clinicHrProfileRepo.findById(1L)).thenReturn(Optional.of(clinicHrProfile));

        clinicHrService.deleteClinicHr(1L);
        verify(clinicHrProfileRepo, times(1)).save(any(ClinicHrProfile.class));
        assertEquals(ClinicHrStatus.DELETED, clinicHrProfile.getClinicHrStatus());
    }
}