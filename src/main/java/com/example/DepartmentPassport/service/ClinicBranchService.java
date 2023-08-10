package com.example.DepartmentPassport.service;


import com.example.DepartmentPassport.model.dto.ClinicBranchRequest;
import com.example.DepartmentPassport.model.dto.ClinicBranchResponse;

public interface ClinicBranchService {
    ClinicBranchResponse createClinicBranch(ClinicBranchRequest clinicBranchRequest);

    ClinicBranchResponse getClinicBranch (Long id);

    ClinicBranchResponse updateClinicBranch (Long id, ClinicBranchRequest clinicBranchRequest);

    void deleteClinicBranch (Long id);

//    Page<BuildingResponse> getAllBuilding (Integer page, Integer perPage, String sort, Sort.Direction order, String filter);
//
//    BuildingResponse addCarToUser(Long carId, Long userId);
}
