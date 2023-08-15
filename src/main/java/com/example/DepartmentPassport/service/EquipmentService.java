package com.example.DepartmentPassport.service;

import com.example.DepartmentPassport.model.dto.EquipmentResponse;
import com.example.DepartmentPassport.model.dto.EquipmentRequest;
import com.example.DepartmentPassport.model.dto.EquipmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface EquipmentService {
    EquipmentResponse createEquipment(EquipmentRequest equipmentRequest);

    EquipmentResponse getEquipment (Long id);

    EquipmentResponse updateEquipment (Long id, EquipmentRequest equipmentRequest);

    void deleteEquipment (Long id);

    EquipmentResponse addEquipmentToDepartment(Long equipmentId, Long departmentId);

    Page<EquipmentResponse> getAllEquipments(Integer page, Integer perPage, String sort, Sort.Direction order, String filter);

}
