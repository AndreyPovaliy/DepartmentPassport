package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.model.dto.EquipmentRequest;
import com.example.DepartmentPassport.model.dto.EquipmentResponse;
import com.example.DepartmentPassport.model.repositories.EquipmentProfileRepo;
import com.example.DepartmentPassport.service.EquipmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentProfileRepo equipmentProfileRepo;
    private final ObjectMapper mapper;
    @Override
    public EquipmentResponse createEquipment(EquipmentRequest equipmentRequest) {
        return null;
    }

    @Override
    public EquipmentResponse getEquipment(Long id) {
        return null;
    }

    @Override
    public EquipmentResponse updateEquipment(Long id, EquipmentRequest equipmentRequest) {
        return null;
    }

    @Override
    public void deleteEquipment(Long id) {

    }
}
