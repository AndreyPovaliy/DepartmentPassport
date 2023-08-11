package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.exceptions.CustomException;
import com.example.DepartmentPassport.model.dto.ClinicBranchRequest;
import com.example.DepartmentPassport.model.dto.ClinicBranchResponse;
import com.example.DepartmentPassport.model.entity.ClinicBranchProfile;
import com.example.DepartmentPassport.model.enums.clinicBranch.ClinicBranchStatus;
import com.example.DepartmentPassport.model.repositories.ClinicBranchProfileRepo;
import com.example.DepartmentPassport.service.ClinicBranchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClinicBranchServiceImpl implements ClinicBranchService {
    private final ClinicBranchProfileRepo clinicBranchProfileRepo;
    private final ObjectMapper mapper;


    @Override
    public ClinicBranchResponse createClinicBranch(ClinicBranchRequest clinicBranchRequest) {
        ClinicBranchProfile clinicBranchProfile = mapper.convertValue(clinicBranchRequest, ClinicBranchProfile.class);
        clinicBranchProfile.setCreatedAt(LocalDateTime.now());
        clinicBranchProfile.setClinicBranchStatus(ClinicBranchStatus.CREATED);

        ClinicBranchProfile save = clinicBranchProfileRepo.save(clinicBranchProfile);
        return mapper.convertValue(save, ClinicBranchResponse.class);
    }

    @Override
    public ClinicBranchResponse getClinicBranch(Long id) {
        ClinicBranchProfile clinicBranch = getClinicBranchById(id);
        return mapper.convertValue(clinicBranch, ClinicBranchResponse.class);
    }

    private ClinicBranchProfile getClinicBranchById (Long id) {
        return clinicBranchProfileRepo.findById(id)
                .orElseThrow(() -> new CustomException("ClinicBranch is not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public ClinicBranchResponse updateClinicBranch(Long id, ClinicBranchRequest clinicBranchRequest) {
        ClinicBranchProfile clinicBranch = getClinicBranchById(id);

        clinicBranch.setFullName(StringUtils.isBlank(clinicBranchRequest.getFullName()) ? clinicBranch.getFullName() : clinicBranchRequest.getFullName());
        clinicBranch.setRealAddress(StringUtils.isBlank(clinicBranchRequest.getRealAddress()) ? clinicBranch.getRealAddress() : clinicBranchRequest.getRealAddress());

        clinicBranch.setClinicBranchStatus(ClinicBranchStatus.UPDATED);
        clinicBranch.setUpdateAt(LocalDateTime.now());

        ClinicBranchProfile save = clinicBranchProfileRepo.save(clinicBranch);

        return mapper.convertValue(save, ClinicBranchResponse.class);
    }

    @Override
    public void deleteClinicBranch(Long id) {
        ClinicBranchProfile clinicBranch = getClinicBranchById(id);
        clinicBranch.setClinicBranchStatus(ClinicBranchStatus.DELETED);
        clinicBranch.setUpdateAt(LocalDateTime.now());

        clinicBranchProfileRepo.save(clinicBranch);

    }
}
