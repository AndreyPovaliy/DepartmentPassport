package com.example.DepartmentPassport.service;


import com.example.DepartmentPassport.model.dto.DrugRequest;
import com.example.DepartmentPassport.model.dto.DrugResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface DrugService {
    DrugResponse createDrug (DrugRequest drugRequest);

    DrugResponse getDrug (Long id);

    DrugResponse updateDrug (Long id, DrugRequest drugRequest);

    void deleteDrug (Long id);

    DrugResponse addDrugToDepartment(Long drugId, Long departmentId);

    Page<DrugResponse> getAllDrugs(Integer page, Integer perPage, String sort, Sort.Direction order, String filter);

}
