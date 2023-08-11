package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.exceptions.CustomException;
import com.example.DepartmentPassport.model.dto.DepartmentRequest;
import com.example.DepartmentPassport.model.dto.DepartmentResponse;
import com.example.DepartmentPassport.model.entity.DepartmentProfile;
import com.example.DepartmentPassport.model.enums.department.*;
import com.example.DepartmentPassport.model.repositories.DepartmentProfileRepo;
import com.example.DepartmentPassport.service.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentProfileRepo departmentProfileRepo;
    private final ObjectMapper mapper;


    @Override
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        DepartmentProfile departmentProfile = mapper.convertValue(departmentRequest, DepartmentProfile.class);
        departmentProfile.setCreatedAt(LocalDateTime.now());
        departmentProfile.setDepartmentStatus(DepartmentStatus.CREATED);

        DepartmentProfile save = departmentProfileRepo.save(departmentProfile);
        return mapper.convertValue(save, DepartmentResponse.class);
    }

    @Override
    public DepartmentResponse getDepartment(Long id) {
        DepartmentProfile department = getDepartmentById(id);
        return mapper.convertValue(department, DepartmentResponse.class);
    }

    private DepartmentProfile getDepartmentById (Long id) {
        return departmentProfileRepo.findById(id)
                .orElseThrow(() -> new CustomException("Department is not found", HttpStatus.NOT_FOUND));
    }



    @Override
    public DepartmentResponse updateDepartment(Long id, DepartmentRequest departmentRequest) {
        DepartmentProfile department = getDepartmentById(id);

        department.setName(StringUtils.isBlank(departmentRequest.getName()) ? department.getName() : departmentRequest.getName());
        department.setPhone(StringUtils.isBlank(departmentRequest.getPhone()) ? department.getPhone() : departmentRequest.getPhone());
        department.setDepartmentType(departmentRequest.getDepartmentType() == null ? department.getDepartmentType() : departmentRequest.getDepartmentType());
        department.setAssistanceProfile (departmentRequest.getAssistanceProfile() == null ? department.getAssistanceProfile() : departmentRequest.getAssistanceProfile());
        department.setAssistanceConditions (departmentRequest.getAssistanceConditions() == null ? department.getAssistanceConditions() : departmentRequest.getAssistanceConditions());
        department.setAssistanceForm(departmentRequest.getAssistanceForm() == null ? department.getAssistanceForm() : departmentRequest.getAssistanceForm());
        department.setNumberPositionsDoctors(departmentRequest.getNumberPositionsDoctors() == null ? department.getNumberPositionsDoctors() : departmentRequest.getNumberPositionsDoctors());
        department.setNumberPositionsNurseStaff(departmentRequest.getNumberPositionsNurseStaff() == null ? department.getNumberPositionsNurseStaff() : departmentRequest.getNumberPositionsNurseStaff());
        department.setNumberPositionsJuniorMedicalStaff(departmentRequest.getNumberPositionsJuniorMedicalStaff() == null ? department.getNumberPositionsJuniorMedicalStaff() : departmentRequest.getNumberPositionsJuniorMedicalStaff());
        department.setNumberInpatientBeds(departmentRequest.getNumberInpatientBeds() == null ? department.getNumberInpatientBeds() : departmentRequest.getNumberInpatientBeds());
        department.setNumberOutpatientBeds(departmentRequest.getNumberOutpatientBeds() == null ? department.getNumberOutpatientBeds() : departmentRequest.getNumberOutpatientBeds());


        department.setDepartmentStatus(DepartmentStatus.UPDATED);
        department.setUpdateAt(LocalDateTime.now());

        DepartmentProfile save = departmentProfileRepo.save(department);

        return mapper.convertValue(save, DepartmentResponse.class);
    }

    @Override
    public void deleteDepartment(Long id) {
        DepartmentProfile department = getDepartmentById(id);
        department.setDepartmentStatus(DepartmentStatus.DELETED);
        department.setUpdateAt(LocalDateTime.now());

        departmentProfileRepo.save(department);

    }
}
