package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.model.dto.AdminHrRequest;
import com.example.DepartmentPassport.model.dto.AdminHrResponse;
import com.example.DepartmentPassport.model.entity.AdminHrProfile;
import com.example.DepartmentPassport.model.enums.adminHR.AdminHrStatus;
import com.example.DepartmentPassport.model.repositories.AdminHrProfileRepo;
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
public class AdminHrServiceImplTest {
    @InjectMocks
    private AdminHrServiceImpl adminHrService;
    @Mock
    private AdminHrProfileRepo adminHrProfileRepo;
    @Spy
    private ObjectMapper mapper;

    @Test
    public void createAdminHr() {
        AdminHrRequest adminHrRequest = new AdminHrRequest();
        adminHrRequest.setFirstName("Алексей");

        AdminHrProfile adminHrProfile = new AdminHrProfile();
        adminHrProfile.setId(1L);

        when(adminHrProfileRepo.save(any(AdminHrProfile.class))).thenReturn(adminHrProfile);

        AdminHrResponse result = adminHrService.createAdminHr(adminHrRequest);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
    }

    @Test
    public void getAdminHr() {
        AdminHrProfile adminHrProfile = new AdminHrProfile();
        adminHrProfile.setId(1L);

        when(adminHrProfileRepo.findById(1L)).thenReturn(Optional.of(adminHrProfile));

        AdminHrResponse result = adminHrService.getAdminHr(1L);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
        assertEquals(adminHrProfile.getId(), result.getId());
    }

    @Test
    public void updateAdminHr() {
        AdminHrProfile adminHrProfile = new AdminHrProfile();
        adminHrProfile.setId(1L);

        when (adminHrProfileRepo.findById(1L)).thenReturn(Optional.of(adminHrProfile));

        AdminHrRequest adminHrRequest = new AdminHrRequest();
        adminHrService.updateAdminHr(1L, adminHrRequest);
        verify(adminHrProfileRepo, times(1)).save(any(AdminHrProfile.class));
        assertEquals(AdminHrStatus.UPDATED, adminHrProfile.getAdminHrStatus());
    }

    @Test
    public void deleteAdminHr() {
        AdminHrProfile adminHrProfile = new AdminHrProfile();
        adminHrProfile.setId(1L);

        when (adminHrProfileRepo.findById(1L)).thenReturn(Optional.of(adminHrProfile));

        adminHrService.deleteAdminHr(1L);
        verify(adminHrProfileRepo, times(1)).save(any(AdminHrProfile.class));
        assertEquals(AdminHrStatus.DELETED, adminHrProfile.getAdminHrStatus());
    }
}