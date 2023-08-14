package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.exceptions.CustomException;
import com.example.DepartmentPassport.model.dto.ClinicBranchRequest;
import com.example.DepartmentPassport.model.dto.ClinicBranchResponse;
import com.example.DepartmentPassport.model.dto.ClinicResponse;
import com.example.DepartmentPassport.model.entity.ClinicBranchProfile;
import com.example.DepartmentPassport.model.entity.ClinicProfile;
import com.example.DepartmentPassport.model.enums.clinicBranch.ClinicBranchStatus;
import com.example.DepartmentPassport.model.repositories.ClinicBranchProfileRepo;
import com.example.DepartmentPassport.service.ClinicBranchService;
import com.example.DepartmentPassport.service.ClinicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.DepartmentPassport.utils.PaginationUtil.getPageRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClinicBranchServiceImpl implements ClinicBranchService {
    private final ClinicBranchProfileRepo clinicBranchProfileRepo;
    private final ObjectMapper mapper;
    private final ClinicService clinicService;


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

    @Override
    public ClinicBranchProfile getClinicBranchProfile(Long id) {
        return clinicBranchProfileRepo.findById(id)
                .orElseThrow(() -> new CustomException("User is not found", HttpStatus.NOT_FOUND));
    }


    @Override
    public ClinicBranchResponse addBranchToClinic(Long branchId, Long clinicId) {
        ClinicBranchProfile clinicBranchProfile = getClinicBranchById(branchId);
        ClinicProfile clinicProfile = clinicService.getClinicProfile(clinicId);

        clinicProfile.getClinicBranchProfileList().add(clinicBranchProfile);
        clinicService.updateClinicBranchList(clinicProfile);

        clinicBranchProfile.setClinicProfile(clinicProfile);
        ClinicBranchProfile save = clinicBranchProfileRepo.save(clinicBranchProfile);

        ClinicBranchResponse clinicBranchResponse = mapper.convertValue(save, ClinicBranchResponse.class);
        ClinicResponse clinic = mapper.convertValue(clinicProfile, ClinicResponse.class);
        clinicBranchResponse.setClinic(clinic);

        return clinicBranchResponse;

    }



    @Override
    public Page<ClinicBranchResponse> getAllClinicBranches(Integer page, Integer perPage,String sort, Sort.Direction order, String filter) {
        Pageable pageRequest = getPageRequest(page, perPage, sort, order);

        Page<ClinicBranchProfile> pageList = clinicBranchProfileRepo.findAllNotDeleted(pageRequest);
        List<ClinicBranchResponse> responses = pageList.getContent().stream()
                .map(c -> mapper.convertValue(c, ClinicBranchResponse.class))
                .collect(Collectors.toList());
        return new PageImpl<>(responses);
    }

    @Override
    public void updateDepartmentList(ClinicBranchProfile clinicBranchProfile) {
        clinicBranchProfileRepo.save(clinicBranchProfile);
    }
}
