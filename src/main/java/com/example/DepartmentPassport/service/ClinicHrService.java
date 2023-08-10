package com.example.DepartmentPassport.service;

import com.example.DepartmentPassport.model.dto.ClinicHrRequest;
import com.example.DepartmentPassport.model.dto.ClinicHrResponse;

public interface ClinicHrService {
    ClinicHrResponse createClinicHr(ClinicHrRequest clinicHrRequest);

    ClinicHrResponse getClinicHr (Long id);

    ClinicHrResponse updateClinicHr (Long id, ClinicHrRequest clinicHrRequest);

    void deleteClinicHr (Long id);

//    Page<BuildingResponse> getAllHr (Integer page, Integer perPage, String sort, Sort.Direction order, String filter);
//
//    BuildingResponse addCarToUser(Long carId, Long userId);
}
