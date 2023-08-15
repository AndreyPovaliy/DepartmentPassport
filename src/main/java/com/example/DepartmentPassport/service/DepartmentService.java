package com.example.DepartmentPassport.service;


import com.example.DepartmentPassport.model.dto.DepartmentRequest;
import com.example.DepartmentPassport.model.dto.DepartmentResponse;
import com.example.DepartmentPassport.model.entity.DepartmentProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;


public interface DepartmentService {
    DepartmentResponse createDepartment(DepartmentRequest departmentRequest);

    DepartmentResponse getDepartment (Long id);

    DepartmentResponse updateDepartment (Long id, DepartmentRequest departmentRequest);

    void deleteDepartment (Long id);

    DepartmentProfile getDepartmentProfile(Long id);

    void updateAdminHrList(DepartmentProfile departmentProfile);

    void updateClinicHrList(DepartmentProfile departmentProfile);

    void updateEquipmentList(DepartmentProfile departmentProfile);

    void updateMedicalDeviceList(DepartmentProfile departmentProfile);

    void updateRoomList(DepartmentProfile departmentProfile);

    void updateDrugList(DepartmentProfile departmentProfile);

    DepartmentResponse addDepartmentToBranch(Long departmentId, Long branchId);

    Page<DepartmentResponse> getAllDepartments(Integer page, Integer perPage, String sort, Sort.Direction order, String filter);
}
