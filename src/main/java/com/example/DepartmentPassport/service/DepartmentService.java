package com.example.DepartmentPassport.service;

import com.example.DepartmentPassport.model.dto.DepartmentRequest;
import com.example.DepartmentPassport.model.dto.DepartmentResponse;

public interface DepartmentService {
    DepartmentResponse createDepartment(DepartmentRequest departmentRequest);

    DepartmentResponse getDepartment (Long id);

    DepartmentResponse updateDepartment (Long id, DepartmentRequest departmentRequest);

    void deleteDepartment (Long id);

//    Page<BuildingResponse> getAllBuilding (Integer page, Integer perPage, String sort, Sort.Direction order, String filter);
//
//    BuildingResponse addCarToUser(Long carId, Long userId);
}
