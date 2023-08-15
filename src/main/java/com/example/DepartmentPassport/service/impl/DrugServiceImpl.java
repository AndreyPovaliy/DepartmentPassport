package com.example.DepartmentPassport.service.impl;


import com.example.DepartmentPassport.exceptions.CustomException;
import com.example.DepartmentPassport.model.dto.DrugResponse;
import com.example.DepartmentPassport.model.dto.DepartmentResponse;
import com.example.DepartmentPassport.model.dto.DrugRequest;

import com.example.DepartmentPassport.model.entity.DrugProfile;
import com.example.DepartmentPassport.model.entity.DepartmentProfile;
import com.example.DepartmentPassport.model.enums.drug.DrugStatus;
import com.example.DepartmentPassport.model.repositories.DrugProfileRepo;
import com.example.DepartmentPassport.service.DepartmentService;
import com.example.DepartmentPassport.service.DrugService;
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
public class DrugServiceImpl implements DrugService {
    private final DrugProfileRepo drugProfileRepo;
    private final ObjectMapper mapper;
    private final DepartmentService departmentService;


    @Override
    public DrugResponse createDrug(DrugRequest drugRequest) {
        DrugProfile drugProfile = mapper.convertValue(drugRequest, DrugProfile.class);
        drugProfile.setCreatedAt(LocalDateTime.now());
        drugProfile.setDrugStatus(DrugStatus.CREATED);

        DrugProfile save = drugProfileRepo.save(drugProfile);
        return mapper.convertValue(save, DrugResponse.class);
    }

    @Override
    public DrugResponse getDrug(Long id) {
        DrugProfile drug = getDrugById(id);
        return mapper.convertValue(drug, DrugResponse.class);
    }

    private DrugProfile getDrugById (Long id) {
        return drugProfileRepo.findById(id)
                .orElseThrow(() -> new CustomException("Drug is not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public DrugResponse updateDrug(Long id, DrugRequest drugRequest) {
        DrugProfile drug = getDrugById(id);

        drug.setTradeName(StringUtils.isBlank(drugRequest.getTradeName()) ? drug.getTradeName() : drugRequest.getTradeName());
        drug.setReleaseForm(drugRequest.getReleaseForm() == null ? drug.getReleaseForm() : drugRequest.getReleaseForm());
        drug.setPackingVolume(drugRequest.getPackingVolume() == null ? drug.getPackingVolume() : drugRequest.getPackingVolume());
        drug.setMeasure(StringUtils.isBlank(drugRequest.getMeasure()) ? drug.getMeasure() : drugRequest.getMeasure());
        drug.setProductionDate(drugRequest.getProductionDate() == null ? drug.getProductionDate() : drugRequest.getProductionDate());
        drug.setBestBeforeDate(drugRequest.getBestBeforeDate() == null ? drug.getBestBeforeDate() : drugRequest.getBestBeforeDate());

        drug.setDrugStatus(DrugStatus.UPDATED);
        drug.setUpdateAt(LocalDateTime.now());

        DrugProfile save = drugProfileRepo.save(drug);

        return mapper.convertValue(save, DrugResponse.class);
    }

    @Override
    public void deleteDrug(Long id) {
        DrugProfile drug = getDrugById(id);
        drug.setDrugStatus(DrugStatus.DELETED);
        drug.setUpdateAt(LocalDateTime.now());

        drugProfileRepo.save(drug);

    }

    @Override
    public DrugResponse addDrugToDepartment(Long drugId, Long departmentId) {
        DrugProfile drugProfile = getDrugById(drugId);
        DepartmentProfile departmentProfile = departmentService.getDepartmentProfile(departmentId);

        departmentProfile.getDrugProfiles().add(drugProfile);
        departmentService.updateDrugList(departmentProfile);

        drugProfile.setDepartmentProfile(departmentProfile);
        DrugProfile save = drugProfileRepo.save(drugProfile);

        DrugResponse DrugResponse = mapper.convertValue(save, DrugResponse.class);
        DepartmentResponse department = mapper.convertValue(departmentProfile, DepartmentResponse.class);
        DrugResponse.setDepartmentResponse(department);

        return DrugResponse;
    }

    @Override
    public Page<DrugResponse> getAllDrugs(Integer page, Integer perPage, String sort, Sort.Direction order, String filter) {
        Pageable pageRequest = getPageRequest(page, perPage, sort, order);

        Page<DrugProfile> pageList = drugProfileRepo.findAllNotDeleted(pageRequest);
        List<DrugResponse> responses = pageList.getContent().stream()
                .map(c -> mapper.convertValue(c, DrugResponse.class))
                .collect(Collectors.toList());
        return new PageImpl<>(responses);
    }
}
