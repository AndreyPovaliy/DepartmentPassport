package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.exceptions.CustomException;
import com.example.DepartmentPassport.model.dto.MedicalDeviceRequest;
import com.example.DepartmentPassport.model.dto.MedicalDeviceResponse;
import com.example.DepartmentPassport.model.entity.MedicalDeviceProfile;
import com.example.DepartmentPassport.model.enums.medicalDevice.MedicalDeviceStatus;
import com.example.DepartmentPassport.model.repositories.MedicalDeviceProfileRepo;
import com.example.DepartmentPassport.service.MedicalDeviceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class MedicalDeviceServiceImpl implements MedicalDeviceService {
    private final MedicalDeviceProfileRepo medicalDeviceProfileRepo;
    private final ObjectMapper mapper;


    @Override
    public MedicalDeviceResponse createMedicalDevice(MedicalDeviceRequest medicalDeviceRequest) {
        MedicalDeviceProfile medicalDeviceProfile = mapper.convertValue(medicalDeviceRequest, MedicalDeviceProfile.class);
        medicalDeviceProfile.setCreatedAt(LocalDateTime.now());
        medicalDeviceProfile.setMedicalDeviceStatus(MedicalDeviceStatus.CREATED);

        MedicalDeviceProfile save = medicalDeviceProfileRepo.save(medicalDeviceProfile);
        return mapper.convertValue(save, MedicalDeviceResponse.class);
    }

    @Override
    public MedicalDeviceResponse getMedicalDevice(Long id) {
        MedicalDeviceProfile medicalDevice = getMedicalDeviceById(id);
        return mapper.convertValue(medicalDevice, MedicalDeviceResponse.class);
    }

    private MedicalDeviceProfile getMedicalDeviceById (Long id) {
        return medicalDeviceProfileRepo.findById(id)
                .orElseThrow(() -> new CustomException("MedicalDevice is not found", HttpStatus.NOT_FOUND));
    }



    @Override
    public MedicalDeviceResponse updateMedicalDevice(Long id, MedicalDeviceRequest medicalDeviceRequest) {
        MedicalDeviceProfile medicalDevice = getMedicalDeviceById(id);

        medicalDevice.setName(StringUtils.isBlank(medicalDeviceRequest.getName()) ? medicalDevice.getName() : medicalDeviceRequest.getName());
        medicalDevice.setInventoryNumber(StringUtils.isBlank(medicalDeviceRequest.getInventoryNumber()) ? medicalDevice.getInventoryNumber() : medicalDeviceRequest.getInventoryNumber());
        medicalDevice.setStartExploitation(medicalDeviceRequest.getStartExploitation() == null ? medicalDevice.getStartExploitation() : medicalDeviceRequest.getStartExploitation());
        medicalDevice.setLifeTime(medicalDeviceRequest.getLifeTime() == null ? medicalDevice.getLifeTime() : medicalDeviceRequest.getLifeTime());
        medicalDevice.setIsSterilization(medicalDeviceRequest.getIsSterilization() == null ? medicalDevice.getIsSterilization() : medicalDeviceRequest.getIsSterilization());
        medicalDevice.setIsOneOff(medicalDeviceRequest.getIsOneOff() == null ? medicalDevice.getIsOneOff() : medicalDeviceRequest.getIsOneOff());

        medicalDevice.setMedicalDeviceStatus(MedicalDeviceStatus.UPDATED);
        medicalDevice.setUpdateAt(LocalDateTime.now());

        MedicalDeviceProfile save = medicalDeviceProfileRepo.save(medicalDevice);

        return mapper.convertValue(save, MedicalDeviceResponse.class);
    }

    @Override
    public void deleteMedicalDevice(Long id) {
        MedicalDeviceProfile medicalDevice = getMedicalDeviceById(id);
        medicalDevice.setMedicalDeviceStatus(MedicalDeviceStatus.DELETED);
        medicalDevice.setUpdateAt(LocalDateTime.now());

        medicalDeviceProfileRepo.save(medicalDevice);

    }
}
