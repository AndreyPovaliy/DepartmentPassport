package com.example.DepartmentPassport.service;

import com.example.DepartmentPassport.model.dto.AdminHrRequest;
import com.example.DepartmentPassport.model.dto.AdminHrResponse;


public interface AdminHrService {
    AdminHrResponse createAdminHr (AdminHrRequest adminHrRequest);

    AdminHrResponse getAdminHr (Long id);

    AdminHrResponse updateAdminHr (Long id, AdminHrRequest adminHrRequest);

    void deleteAdminHr (Long id);

//    Page<BuildingResponse> getAllBuilding (Integer page, Integer perPage, String sort, Sort.Direction order, String filter);
//
//    BuildingResponse addCarToUser(Long carId, Long userId);
}
