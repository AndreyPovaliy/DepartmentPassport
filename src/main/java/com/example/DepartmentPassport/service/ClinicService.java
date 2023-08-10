package com.example.DepartmentPassport.service;

import com.example.DepartmentPassport.model.dto.ClinicRequest;
import com.example.DepartmentPassport.model.dto.ClinicResponse;

public interface ClinicService {
    ClinicResponse createClinic(ClinicRequest clinicRequest);

    ClinicResponse getClinic (Long id);

    ClinicResponse updateClinic (Long id, ClinicRequest clinicRequest);

    void deleteClinic (Long id);

//    Page<BuildingResponse> getAllBuilding (Integer page, Integer perPage, String sort, Sort.Direction order, String filter);
//
//    BuildingResponse addCarToUser(Long carId, Long userId);
}
