package com.example.DepartmentPassport.service;


import com.example.DepartmentPassport.model.dto.ClinicBranchRequest;
import com.example.DepartmentPassport.model.dto.ClinicBranchResponse;
import com.example.DepartmentPassport.model.entity.ClinicBranchProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface ClinicBranchService {
    ClinicBranchResponse createClinicBranch(ClinicBranchRequest clinicBranchRequest);

    ClinicBranchResponse getClinicBranch (Long id);

    ClinicBranchResponse updateClinicBranch (Long id, ClinicBranchRequest clinicBranchRequest);

    void deleteClinicBranch (Long id);

    ClinicBranchProfile getClinicBranchProfile(Long id);

    ClinicBranchResponse addBranchToClinic(Long branchId, Long clinicId);

    Page<ClinicBranchResponse> getAllClinicBranches(Integer page, Integer perPage, String sort, Sort.Direction order, String filter);


    void updateDepartmentList(ClinicBranchProfile clinicBranchProfile);
}
