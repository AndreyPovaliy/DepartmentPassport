package com.example.DepartmentPassport.service;

import com.example.DepartmentPassport.model.dto.ClinicRequest;
import com.example.DepartmentPassport.model.dto.ClinicResponse;
import com.example.DepartmentPassport.model.entity.ClinicProfile;

import java.util.List;

public interface ClinicService {
    ClinicResponse createClinic(ClinicRequest clinicRequest);

    ClinicResponse getClinic (Long id);

    ClinicResponse updateClinic (Long id, ClinicRequest clinicRequest);

    void deleteClinic (Long id);

    ClinicProfile getClinicProfile(Long id);

    void updateClinicBranchList(ClinicProfile clinicProfile);

    List<ClinicResponse> getAllClinics();



//    Page<BuildingResponse> getAllBuilding (Integer page, Integer perPage, String sort, Sort.Direction order, String filter);
//
//    BuildingResponse addCarToUser(Long carId, Long userId);
}
