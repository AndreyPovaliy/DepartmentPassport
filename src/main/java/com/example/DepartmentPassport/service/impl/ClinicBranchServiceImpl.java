package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.model.dto.ClinicBranchRequest;
import com.example.DepartmentPassport.model.dto.ClinicBranchResponse;
import com.example.DepartmentPassport.model.repositories.ClinicBranchProfileRepo;
import com.example.DepartmentPassport.service.ClinicBranchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClinicBranchServiceImpl implements ClinicBranchService {
    private final ClinicBranchProfileRepo clinicBranchProfileRepo;
    private final ObjectMapper mapper;
    @Override
    public ClinicBranchResponse createClinicBranch(ClinicBranchRequest clinicBranchRequest) {
        return null;
    }

    @Override
    public ClinicBranchResponse getClinicBranch(Long id) {
        return null;
    }

    @Override
    public ClinicBranchResponse updateClinicBranch(Long id, ClinicBranchRequest clinicBranchRequest) {
        return null;
    }

    @Override
    public void deleteClinicBranch(Long id) {

    }
}
