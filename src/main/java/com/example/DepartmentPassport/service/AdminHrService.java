package com.example.DepartmentPassport.service;

import com.example.DepartmentPassport.model.dto.AdminHrRequest;
import com.example.DepartmentPassport.model.dto.AdminHrResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;


public interface AdminHrService {
    AdminHrResponse createAdminHr (AdminHrRequest adminHrRequest);

    AdminHrResponse getAdminHr (Long id);

    AdminHrResponse updateAdminHr (Long id, AdminHrRequest adminHrRequest);

    void deleteAdminHr (Long id);

    AdminHrResponse addAdminHrToDepartment(Long adminHrId, Long departmentId);

    Page<AdminHrResponse> getAllAdminHrs(Integer page, Integer perPage, String sort, Sort.Direction order, String filter);
}

