package com.example.DepartmentPassport.service;

import com.example.DepartmentPassport.model.dto.AdminHrResponse;
import com.example.DepartmentPassport.model.dto.ClinicHrRequest;
import com.example.DepartmentPassport.model.dto.ClinicHrResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface ClinicHrService {
    ClinicHrResponse createClinicHr(ClinicHrRequest clinicHrRequest);

    ClinicHrResponse getClinicHr (Long id);

    ClinicHrResponse updateClinicHr (Long id, ClinicHrRequest clinicHrRequest);

    void deleteClinicHr (Long id);

    ClinicHrResponse addClinicHrToDepartment(Long adminHrId, Long departmentId);

    Page<ClinicHrResponse> getAllClinicHrs(Integer page, Integer perPage, String sort, Sort.Direction order, String filter);

}
