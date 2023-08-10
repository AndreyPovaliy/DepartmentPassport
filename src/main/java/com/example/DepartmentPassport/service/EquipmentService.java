package com.example.DepartmentPassport.service;

import com.example.DepartmentPassport.model.dto.EquipmentRequest;
import com.example.DepartmentPassport.model.dto.EquipmentResponse;

public interface EquipmentService {
    EquipmentResponse createEquipment(EquipmentRequest equipmentRequest);

    EquipmentResponse getEquipment (Long id);

    EquipmentResponse updateEquipment (Long id, EquipmentRequest equipmentRequest);

    void deleteEquipment (Long id);

//    Page<BuildingResponse> getAllBuilding (Integer page, Integer perPage, String sort, Sort.Direction order, String filter);
//
//    BuildingResponse addCarToUser(Long carId, Long userId);
}
