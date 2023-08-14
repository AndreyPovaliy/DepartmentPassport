package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.exceptions.CustomException;
import com.example.DepartmentPassport.model.dto.AdminHrRequest;
import com.example.DepartmentPassport.model.dto.AdminHrResponse;
import com.example.DepartmentPassport.model.dto.DepartmentResponse;
import com.example.DepartmentPassport.model.entity.AdminHrProfile;
import com.example.DepartmentPassport.model.entity.DepartmentProfile;
import com.example.DepartmentPassport.model.enums.adminHR.AdminHrStatus;
import com.example.DepartmentPassport.model.repositories.AdminHrProfileRepo;
import com.example.DepartmentPassport.service.AdminHrService;
import com.example.DepartmentPassport.service.DepartmentService;
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
public class AdminHrServiceImpl implements AdminHrService {
    private final AdminHrProfileRepo adminHrProfileRepo;
    private final ObjectMapper mapper;
    private final DepartmentService departmentService;


    @Override
    public AdminHrResponse createAdminHr(AdminHrRequest adminHrRequest) {
        AdminHrProfile adminHrProfile = mapper.convertValue(adminHrRequest, AdminHrProfile.class);
        adminHrProfile.setCreatedAt(LocalDateTime.now());
        adminHrProfile.setAdminHrStatus(AdminHrStatus.CREATED);

        AdminHrProfile save = adminHrProfileRepo.save(adminHrProfile);
        return mapper.convertValue(save, AdminHrResponse.class);
    }

    @Override
    public AdminHrResponse getAdminHr(Long id) {
        AdminHrProfile AdminHr = getAdminHrById(id);
        return mapper.convertValue(AdminHr, AdminHrResponse.class);
    }

    private AdminHrProfile getAdminHrById (Long id) {
        return adminHrProfileRepo.findById(id)
                .orElseThrow(() -> new CustomException("AdminHr is not found", HttpStatus.NOT_FOUND));
    }



    @Override
    public AdminHrResponse updateAdminHr(Long id, AdminHrRequest adminHrRequest) {
        AdminHrProfile adminHr = getAdminHrById(id);

        adminHr.setJobTitle(StringUtils.isBlank(adminHrRequest.getJobTitle()) ? adminHr.getJobTitle() : adminHrRequest.getJobTitle());
        adminHr.setFirstName(StringUtils.isBlank(adminHrRequest.getFirstName()) ? adminHr.getFirstName() : adminHrRequest.getFirstName());
        adminHr.setLastName(StringUtils.isBlank(adminHrRequest.getLastName()) ? adminHr.getLastName() : adminHrRequest.getLastName());
        adminHr.setMiddleName(StringUtils.isBlank(adminHrRequest.getMiddleName()) ? adminHr.getMiddleName() : adminHrRequest.getMiddleName());
        adminHr.setPhone(StringUtils.isBlank(adminHrRequest.getPhone()) ? adminHr.getPhone() : adminHrRequest.getPhone());

        adminHr.setAdminHrStatus(AdminHrStatus.UPDATED);
        adminHr.setUpdateAt(LocalDateTime.now());

        AdminHrProfile save = adminHrProfileRepo.save(adminHr);

        return mapper.convertValue(save, AdminHrResponse.class);
    }

    @Override
    public void deleteAdminHr(Long id) {
        AdminHrProfile adminHr = getAdminHrById(id);
        adminHr.setAdminHrStatus(AdminHrStatus.DELETED);
        adminHr.setUpdateAt(LocalDateTime.now());

        adminHrProfileRepo.save(adminHr);

    }

    @Override
    public AdminHrResponse addAdminHrToDepartment(Long adminHrId, Long departmentId) {
        AdminHrProfile adminHrProfile = getAdminHrById(adminHrId);
        DepartmentProfile departmentProfile = departmentService.getDepartmentProfile(departmentId);

        departmentProfile.getAdminHrProfiles().add(adminHrProfile);
        departmentService.updateAdminHrList(departmentProfile);

        adminHrProfile.setDepartmentProfile(departmentProfile);
        AdminHrProfile save = adminHrProfileRepo.save(adminHrProfile);

        AdminHrResponse adminHrResponse = mapper.convertValue(save, AdminHrResponse.class);
        DepartmentResponse department = mapper.convertValue(departmentProfile, DepartmentResponse.class);
        adminHrResponse.setDepartmentResponse(department);

        return adminHrResponse;
    }

    @Override
    public Page<AdminHrResponse> getAllAdminHrs(Integer page, Integer perPage, String sort, Sort.Direction order, String filter) {
        Pageable pageRequest = getPageRequest(page, perPage, sort, order);

        Page<AdminHrProfile> pageList = adminHrProfileRepo.findAllNotDeleted(pageRequest);
        List<AdminHrResponse> responses = pageList.getContent().stream()
                .map(c -> mapper.convertValue(c, AdminHrResponse.class))
                .collect(Collectors.toList());
        return new PageImpl<>(responses);
    }
}
