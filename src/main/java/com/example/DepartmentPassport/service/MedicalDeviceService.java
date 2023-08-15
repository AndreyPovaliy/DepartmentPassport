package com.example.DepartmentPassport.service;

import com.example.DepartmentPassport.model.dto.MedicalDeviceResponse;
import com.example.DepartmentPassport.model.dto.MedicalDeviceRequest;
import com.example.DepartmentPassport.model.dto.MedicalDeviceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface MedicalDeviceService {
    MedicalDeviceResponse createMedicalDevice(MedicalDeviceRequest medicalDeviceRequest);

    MedicalDeviceResponse getMedicalDevice(Long id);

    MedicalDeviceResponse updateMedicalDevice(Long id, MedicalDeviceRequest medicalDeviceRequest);

    void deleteMedicalDevice(Long id);

    MedicalDeviceResponse addMedicalDeviceToDepartment(Long medicalDeviceId, Long departmentId);

    Page<MedicalDeviceResponse> getAllMedicalDevices(Integer page, Integer perPage, String sort, Sort.Direction order, String filter);

}
