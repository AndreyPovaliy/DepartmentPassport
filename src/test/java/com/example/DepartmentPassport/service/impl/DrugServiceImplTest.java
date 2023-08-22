package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.model.dto.DrugRequest;
import com.example.DepartmentPassport.model.dto.DrugResponse;
import com.example.DepartmentPassport.model.entity.DrugProfile;
import com.example.DepartmentPassport.model.enums.drug.DrugStatus;
import com.example.DepartmentPassport.model.repositories.DrugProfileRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.example.DepartmentPassport.model.enums.drug.ReleaseForm.CAPSULE;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DrugServiceImplTest {
    @InjectMocks
    private DrugServiceImpl drugService;
    @Mock
    private DrugProfileRepo drugProfileRepo;
    @Spy
    private ObjectMapper mapper;

    @Test
    public void createDrug() {
        DrugRequest drugRequest = new DrugRequest();
        drugRequest.setReleaseForm(CAPSULE);

        DrugProfile drugProfile = new DrugProfile();
        drugProfile.setId(1L);

        when(drugProfileRepo.save(any(DrugProfile.class))).thenReturn(drugProfile);

        DrugResponse result = drugService.createDrug(drugRequest);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
    }

    @Test
    public void getDrug() {
        DrugProfile drugProfile = new DrugProfile();
        drugProfile.setId(1L);

        when(drugProfileRepo.findById(1L)).thenReturn(Optional.of(drugProfile));

        DrugResponse result = drugService.getDrug(1L);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
        assertEquals(drugProfile.getId(), result.getId());
    }

    @Test
    public void updateDrug() {
        DrugProfile drugProfile = new DrugProfile();
        drugProfile.setId(1L);

        when (drugProfileRepo.findById(1L)).thenReturn(Optional.of(drugProfile));

        DrugRequest drugRequest = new DrugRequest();
        drugService.updateDrug(1L, drugRequest);
        verify(drugProfileRepo, times(1)).save(any(DrugProfile.class));
        assertEquals(DrugStatus.UPDATED, drugProfile.getDrugStatus());
    }

    @Test
    public void deleteDrug() {
        DrugProfile drugProfile = new DrugProfile();
        drugProfile.setId(1L);

        when (drugProfileRepo.findById(1L)).thenReturn(Optional.of(drugProfile));

        drugService.deleteDrug(1L);
        verify(drugProfileRepo, times(1)).save(any(DrugProfile.class));
        assertEquals(DrugStatus.DELETED, drugProfile.getDrugStatus());
    }
}