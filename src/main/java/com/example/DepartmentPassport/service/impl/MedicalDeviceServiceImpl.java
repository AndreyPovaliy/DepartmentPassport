package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.model.dto.MedicalDeviceRequest;
import com.example.DepartmentPassport.model.dto.MedicalDeviceResponse;
import com.example.DepartmentPassport.model.repositories.MedicalDeviceProfileRepo;
import com.example.DepartmentPassport.service.MedicalDeviceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MedicalDeviceServiceImpl implements MedicalDeviceService {
    private final MedicalDeviceProfileRepo medicalDeviceProfileRepo;
    private final ObjectMapper mapper;
    @Override
    public MedicalDeviceResponse createMedicalDevice(MedicalDeviceRequest medicalDeviceRequest) {
        return null;
    }

    @Override
    public MedicalDeviceResponse getMedicalDevice(Long id) {
        return null;
    }

    @Override
    public MedicalDeviceResponse updateMedicalDevice(Long id, MedicalDeviceRequest medicalDeviceRequest) {
        return null;
    }

    @Override
    public void deleteMedicalDevice(Long id) {

    }
}
