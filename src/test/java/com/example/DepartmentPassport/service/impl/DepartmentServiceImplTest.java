package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.model.dto.DepartmentRequest;
import com.example.DepartmentPassport.model.dto.DepartmentResponse;
import com.example.DepartmentPassport.model.entity.DepartmentProfile;
import com.example.DepartmentPassport.model.enums.department.DepartmentStatus;
import com.example.DepartmentPassport.model.repositories.DepartmentProfileRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.example.DepartmentPassport.model.enums.department.DepartmentType.THERAPEUTIC;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceImplTest {
    @InjectMocks
    private DepartmentServiceImpl departmentService;
    @Mock
    private DepartmentProfileRepo departmentProfileRepo;
    @Spy
    private ObjectMapper mapper;

    @Test
    public void createDepartment() {
        DepartmentRequest departmentRequest = new DepartmentRequest();
        departmentRequest.setDepartmentType(THERAPEUTIC);

        DepartmentProfile departmentProfile = new DepartmentProfile();
        departmentProfile.setId(1L);

        when(departmentProfileRepo.save(any(DepartmentProfile.class))).thenReturn(departmentProfile);

        DepartmentResponse result = departmentService.createDepartment(departmentRequest);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
    }

    @Test
    public void getDepartment() {
        DepartmentProfile departmentProfile = new DepartmentProfile();
        departmentProfile.setId(1L);

        when(departmentProfileRepo.findById(1L)).thenReturn(Optional.of(departmentProfile));

        DepartmentResponse result = departmentService.getDepartment(1L);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
        assertEquals(departmentProfile.getId(), result.getId());
    }

    @Test
    public void updateDepartment() {
        DepartmentProfile departmentProfile = new DepartmentProfile();
        departmentProfile.setId(1L);

        when (departmentProfileRepo.findById(1L)).thenReturn(Optional.of(departmentProfile));

        DepartmentRequest departmentRequest = new DepartmentRequest();
        departmentService.updateDepartment(1L, departmentRequest);
        verify(departmentProfileRepo, times(1)).save(any(DepartmentProfile.class));
        assertEquals(DepartmentStatus.UPDATED, departmentProfile.getDepartmentStatus());
    }

    @Test
    public void deleteDepartment() {
        DepartmentProfile departmentProfile = new DepartmentProfile();
        departmentProfile.setId(1L);

        when (departmentProfileRepo.findById(1L)).thenReturn(Optional.of(departmentProfile));

        departmentService.deleteDepartment(1L);
        verify(departmentProfileRepo, times(1)).save(any(DepartmentProfile.class));
        assertEquals(DepartmentStatus.DELETED, departmentProfile.getDepartmentStatus());
    }

    @Test
    public void updateAdminHrList() {

    }

}