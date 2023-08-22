package com.example.DepartmentPassport.service.impl;


import com.example.DepartmentPassport.model.dto.ClinicRequest;
import com.example.DepartmentPassport.model.dto.ClinicResponse;
import com.example.DepartmentPassport.model.entity.ClinicProfile;
import com.example.DepartmentPassport.model.enums.clinic.ClinicStatus;
import com.example.DepartmentPassport.model.repositories.ClinicProfileRepo;
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
public class ClinicServiceImplTest {
    @InjectMocks
    private ClinicServiceImpl clinicService;
    @Mock
    private ClinicProfileRepo clinicProfileRepo;
    @Spy
    private ObjectMapper mapper;

    @Test
    public void createClinic() {
        ClinicRequest clinicRequest = new ClinicRequest();
        clinicRequest.setDirector("Швец Алексей Иванович");

        ClinicProfile clinicProfile = new ClinicProfile();
        clinicProfile.setId(1L);

        when(clinicProfileRepo.save(any(ClinicProfile.class))).thenReturn(clinicProfile);

        ClinicResponse result = clinicService.createClinic(clinicRequest);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
    }



    @Test
    public void getClinic() {
        ClinicProfile clinicProfile = new ClinicProfile();
        clinicProfile.setId(1L);

        when(clinicProfileRepo.findById(1L)).thenReturn(Optional.of(clinicProfile));

        ClinicResponse result = clinicService.getClinic(1L);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
        assertEquals(clinicProfile.getId(), result.getId());
    }

    @Test
    public void updateClinic() {
        ClinicProfile clinicProfile = new ClinicProfile();
        clinicProfile.setId(1L);

        when (clinicProfileRepo.findById(1L)).thenReturn(Optional.of(clinicProfile));

        ClinicRequest clinicRequest = new ClinicRequest();
        clinicService.updateClinic(1L, clinicRequest);
        verify(clinicProfileRepo, times(1)).save(any(ClinicProfile.class));
        assertEquals(ClinicStatus.UPDATED, clinicProfile.getClinicStatus());
    }

    @Test
    public void deleteClinic() {
        ClinicProfile clinicProfile = new ClinicProfile();
        clinicProfile.setId(1L);

        when (clinicProfileRepo.findById(1L)).thenReturn(Optional.of(clinicProfile));

        clinicService.deleteClinic(1L);
        verify(clinicProfileRepo, times(1)).save(any(ClinicProfile.class));
        assertEquals(ClinicStatus.DELETED, clinicProfile.getClinicStatus());
    }


}