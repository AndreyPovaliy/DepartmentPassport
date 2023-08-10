package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.model.dto.DepartmentRequest;
import com.example.DepartmentPassport.model.dto.DepartmentResponse;
import com.example.DepartmentPassport.model.repositories.DepartmentProfileRepo;
import com.example.DepartmentPassport.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentProfileRepo departmentProfileRepo;
    private final ObjectMapper mapper;
    @Override
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        return null;
    }

    @Override
    public DepartmentResponse getDepartment(Long id) {
        return null;
    }

    @Override
    public DepartmentResponse updateDepartment(Long id, DepartmentRequest departmentRequest) {
        return null;
    }

    @Override
    public void deleteDepartment(Long id) {

    }
}
