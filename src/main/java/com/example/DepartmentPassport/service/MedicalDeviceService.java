package com.example.DepartmentPassport.service;

import com.example.DepartmentPassport.model.dto.MedicalDeviceRequest;
import com.example.DepartmentPassport.model.dto.MedicalDeviceResponse;

public interface MedicalDeviceService {
    MedicalDeviceResponse createMedicalDevice(MedicalDeviceRequest medicalDeviceRequest);

    MedicalDeviceResponse getMedicalDevice(Long id);

    MedicalDeviceResponse updateMedicalDevice(Long id, MedicalDeviceRequest medicalDeviceRequest);

    void deleteMedicalDevice(Long id);

//    Page<BuildingResponse> getAllBuilding (Integer page, Integer perPage, String sort, Sort.Direction order, String filter);
//
//    BuildingResponse addCarToUser(Long carId, Long userId);
}
