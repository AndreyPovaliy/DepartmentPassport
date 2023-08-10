package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.model.dto.AdminHrRequest;
import com.example.DepartmentPassport.model.dto.AdminHrResponse;
import com.example.DepartmentPassport.model.repositories.AdminHrProfileRepo;
import com.example.DepartmentPassport.service.AdminHrService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminHrServiceImpl implements AdminHrService {
    private final AdminHrProfileRepo adminHrProfileRepo;
    private final ObjectMapper mapper;
    @Override
    public AdminHrResponse createAdminHr(AdminHrRequest adminHrRequest) {
        return null;
    }

    @Override
    public AdminHrResponse getAdminHr(Long id) {
        return null;
    }

    @Override
    public AdminHrResponse updateAdminHr(Long id, AdminHrRequest adminHrRequest) {
        return null;
    }

    @Override
    public void deleteAdminHr(Long id) {

    }
}
