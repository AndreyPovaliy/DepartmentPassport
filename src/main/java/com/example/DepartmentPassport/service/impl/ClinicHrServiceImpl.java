package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.exceptions.CustomException;
import com.example.DepartmentPassport.model.dto.ClinicHrRequest;
import com.example.DepartmentPassport.model.dto.ClinicHrResponse;
import com.example.DepartmentPassport.model.dto.DepartmentResponse;
import com.example.DepartmentPassport.model.entity.ClinicHrProfile;
import com.example.DepartmentPassport.model.entity.DepartmentProfile;
import com.example.DepartmentPassport.model.enums.clinicHR.ClinicHrStatus;
import com.example.DepartmentPassport.model.repositories.ClinicHrProfileRepo;
import com.example.DepartmentPassport.service.ClinicHrService;
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
public class ClinicHrServiceImpl implements ClinicHrService {
    private final ClinicHrProfileRepo clinicHrProfileRepo;
    private final ObjectMapper mapper;
    private final DepartmentService departmentService;

    @Override
    public ClinicHrResponse createClinicHr(ClinicHrRequest clinicHrRequest) {
        ClinicHrProfile clinicHrProfile = mapper.convertValue(clinicHrRequest, ClinicHrProfile.class);
        clinicHrProfile.setCreatedAt(LocalDateTime.now());
        clinicHrProfile.setClinicHrStatus(ClinicHrStatus.CREATED);

        ClinicHrProfile save = clinicHrProfileRepo.save(clinicHrProfile);
        return mapper.convertValue(save, ClinicHrResponse.class);

    }

    @Override
    public ClinicHrResponse getClinicHr(Long id) {
        ClinicHrProfile clinicHr = getClinicHrById(id);
        return mapper.convertValue(clinicHr, ClinicHrResponse.class);
    }

    private ClinicHrProfile getClinicHrById(Long id) {
        return clinicHrProfileRepo.findById(id)
                .orElseThrow(() -> new CustomException("Clinic is not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public ClinicHrResponse updateClinicHr(Long id, ClinicHrRequest clinicHrRequest) {
        ClinicHrProfile clinicHr = getClinicHrById(id);

        clinicHr.setPosition(clinicHrRequest.getPosition() == null ? clinicHr.getPosition() : clinicHrRequest.getPosition());
        clinicHr.setFirstName(StringUtils.isBlank(clinicHrRequest.getFirstName()) ? clinicHr.getFirstName() : clinicHrRequest.getFirstName());
        clinicHr.setLastName(StringUtils.isBlank(clinicHrRequest.getLastName()) ? clinicHr.getLastName() : clinicHrRequest.getLastName());
        clinicHr.setMiddleName(StringUtils.isBlank(clinicHrRequest.getMiddleName()) ? clinicHr.getMiddleName() : clinicHrRequest.getMiddleName());
        clinicHr.setJobTitle(StringUtils.isBlank(clinicHrRequest.getJobTitle()) ? clinicHr.getJobTitle() : clinicHrRequest.getJobTitle());
        clinicHr.setExperience(clinicHrRequest.getExperience() == null ? clinicHr.getExperience() : clinicHrRequest.getExperience());
        clinicHr.setCategory(clinicHrRequest.getCategory() == null ? clinicHr.getCategory() : clinicHrRequest.getCategory());
        clinicHr.setIsAccreditation(clinicHrRequest.getIsAccreditation() == null ? clinicHr.getIsAccreditation() : clinicHrRequest.getIsAccreditation());
        clinicHr.setDateAccreditation(clinicHrRequest.getDateAccreditation() == null ? clinicHr.getDateAccreditation() : clinicHrRequest.getDateAccreditation());


        clinicHr.setClinicHrStatus(ClinicHrStatus.UPDATED);
        clinicHr.setUpdateAt(LocalDateTime.now());

        ClinicHrProfile save = clinicHrProfileRepo.save(clinicHr);

        return mapper.convertValue(save, ClinicHrResponse.class);
    }

    @Override
    public void deleteClinicHr(Long id) {
        ClinicHrProfile clinicHr = getClinicHrById(id);
        clinicHr.setClinicHrStatus(ClinicHrStatus.DELETED);
        clinicHr.setUpdateAt(LocalDateTime.now());

        clinicHrProfileRepo.save(clinicHr);

    }

    @Override
    public ClinicHrResponse addClinicHrToDepartment(Long clinicHrId, Long departmentId) {
        ClinicHrProfile clinicHrProfile = getClinicHrById(clinicHrId);
        DepartmentProfile departmentProfile = departmentService.getDepartmentProfile(departmentId);

        departmentProfile.getClinicHrProfiles().add(clinicHrProfile);
        departmentService.updateClinicHrList(departmentProfile);

        clinicHrProfile.setDepartmentProfile(departmentProfile);
        ClinicHrProfile save = clinicHrProfileRepo.save(clinicHrProfile);

        ClinicHrResponse clinicHrResponse = mapper.convertValue(save, ClinicHrResponse.class);
        DepartmentResponse department = mapper.convertValue(departmentProfile, DepartmentResponse.class);
        clinicHrResponse.setDepartmentResponse(department);

        return clinicHrResponse;
    }

    @Override
    public Page<ClinicHrResponse> getAllClinicHrs(Integer page, Integer perPage, String sort, Sort.Direction order, String filter) {
        Pageable pageRequest = getPageRequest(page, perPage, sort, order);

        Page<ClinicHrProfile> pageList = clinicHrProfileRepo.findAllNotDeleted(pageRequest);
        List<ClinicHrResponse> responses = pageList.getContent().stream()
                .map(c -> mapper.convertValue(c, ClinicHrResponse.class))
                .collect(Collectors.toList());
        return new PageImpl<>(responses);
    }
}
