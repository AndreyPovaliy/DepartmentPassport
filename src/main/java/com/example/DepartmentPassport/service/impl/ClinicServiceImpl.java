package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.exceptions.CustomException;
import com.example.DepartmentPassport.model.dto.ClinicRequest;
import com.example.DepartmentPassport.model.dto.ClinicResponse;
import com.example.DepartmentPassport.model.entity.ClinicProfile;
import com.example.DepartmentPassport.model.enums.clinic.ClinicStatus;
import com.example.DepartmentPassport.model.repositories.ClinicProfileRepo;
import com.example.DepartmentPassport.service.ClinicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class ClinicServiceImpl implements ClinicService {
    private final ClinicProfileRepo clinicProfileRepo;
    private final ObjectMapper mapper;


    @Override
    public ClinicResponse createClinic(ClinicRequest clinicRequest) {
        ClinicProfile clinicProfile = mapper.convertValue(clinicRequest, ClinicProfile.class);
        clinicProfile.setCreatedAt(LocalDateTime.now());
        clinicProfile.setClinicStatus(ClinicStatus.CREATED);

        ClinicProfile save = clinicProfileRepo.save(clinicProfile);
        return mapper.convertValue(save, ClinicResponse.class);
    }

    @Override
    public ClinicResponse getClinic(Long id) {
        ClinicProfile clinic = getClinicById(id);
        return mapper.convertValue(clinic, ClinicResponse.class);
    }

    private ClinicProfile getClinicById(Long id) {
        return clinicProfileRepo.findById(id)
                .orElseThrow(() -> new CustomException("Clinic is not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public ClinicResponse updateClinic(Long id, ClinicRequest clinicRequest) {
        ClinicProfile clinic = getClinicById(id);

        clinic.setFullName(StringUtils.isBlank(clinicRequest.getFullName()) ? clinic.getFullName() : clinicRequest.getFullName());
        clinic.setLegalAddress(StringUtils.isBlank(clinicRequest.getLegalAddress()) ? clinic.getLegalAddress() : clinicRequest.getLegalAddress());
        clinic.setDirector(StringUtils.isBlank(clinicRequest.getDirector()) ? clinic.getDirector() : clinicRequest.getDirector());
        clinic.setWebSite(StringUtils.isBlank(clinicRequest.getWebSite()) ? clinic.getWebSite() : clinicRequest.getWebSite());
        clinic.setPhone(StringUtils.isBlank(clinicRequest.getPhone()) ? clinic.getPhone() : clinicRequest.getPhone());


        clinic.setClinicStatus(ClinicStatus.UPDATED);
        clinic.setUpdateAt(LocalDateTime.now());

        ClinicProfile save = clinicProfileRepo.save(clinic);

        return mapper.convertValue(save, ClinicResponse.class);
    }

    @Override
    public void deleteClinic(Long id) {
        ClinicProfile clinic = getClinicById(id);
        clinic.setClinicStatus(ClinicStatus.DELETED);
        clinic.setUpdateAt(LocalDateTime.now());

        clinicProfileRepo.save(clinic);

    }

    @Override
    public ClinicProfile getClinicProfile(Long id) {
        return clinicProfileRepo.findById(id)
                .orElseThrow(() -> new CustomException("Clinic is not found", HttpStatus.NOT_FOUND));
    }
    @Override
    public void updateClinicBranchList(ClinicProfile clinicProfile) {
        clinicProfileRepo.save(clinicProfile);
    }

    @Override
    public void updateBuildingList(ClinicProfile clinicProfile) {
        clinicProfileRepo.save(clinicProfile);

    }

    @Override
    public List<ClinicResponse> getAllClinics() {
        return clinicProfileRepo.findAll().stream()
                .filter(u -> u.getClinicStatus() != ClinicStatus.DELETED)
                .map(u -> mapper.convertValue(u, ClinicResponse.class))
                .collect(Collectors.toList());
    }
}
