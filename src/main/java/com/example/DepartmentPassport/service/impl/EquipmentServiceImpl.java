package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.exceptions.CustomException;
import com.example.DepartmentPassport.model.dto.DepartmentResponse;
import com.example.DepartmentPassport.model.dto.EquipmentResponse;
import com.example.DepartmentPassport.model.dto.EquipmentRequest;
import com.example.DepartmentPassport.model.entity.DepartmentProfile;
import com.example.DepartmentPassport.model.entity.EquipmentProfile;
import com.example.DepartmentPassport.model.enums.equipment.EquipmentStatus;
import com.example.DepartmentPassport.model.repositories.EquipmentProfileRepo;
import com.example.DepartmentPassport.service.DepartmentService;
import com.example.DepartmentPassport.service.EquipmentService;
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
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentProfileRepo equipmentProfileRepo;
    private final ObjectMapper mapper;
    private final DepartmentService departmentService;



    @Override
    public EquipmentResponse createEquipment(EquipmentRequest equipmentRequest) {
        EquipmentProfile equipmentProfile = mapper.convertValue(equipmentRequest, EquipmentProfile.class);
        equipmentProfile.setCreatedAt(LocalDateTime.now());
        equipmentProfile.setEquipmentStatus(EquipmentStatus.CREATED);

        EquipmentProfile save = equipmentProfileRepo.save(equipmentProfile);
        return mapper.convertValue(save, EquipmentResponse.class);
    }

    @Override
    public EquipmentResponse getEquipment(Long id) {
        EquipmentProfile equipment = getEquipmentById(id);
        return mapper.convertValue(equipment, EquipmentResponse.class);
    }

    private EquipmentProfile getEquipmentById (Long id) {
        return equipmentProfileRepo.findById(id)
                .orElseThrow(() -> new CustomException("Equipment is not found", HttpStatus.NOT_FOUND));
    }


    @Override
    public EquipmentResponse updateEquipment(Long id, EquipmentRequest equipmentRequest) {
        EquipmentProfile equipment = getEquipmentById(id);

        equipment.setName(StringUtils.isBlank(equipmentRequest.getName()) ? equipment.getName() : equipmentRequest.getName());
        equipment.setInventoryNumber(StringUtils.isBlank(equipmentRequest.getName()) ? equipment.getName() : equipmentRequest.getName());
        equipment.setPassport(StringUtils.isBlank(equipmentRequest.getName()) ? equipment.getName() : equipmentRequest.getName());

        equipment.setStartExploitation(equipmentRequest.getStartExploitation() == null ? equipment.getStartExploitation() : equipmentRequest.getStartExploitation());
        equipment.setLifeTime(equipmentRequest.getLifeTime() == null ? equipment.getLifeTime() : equipmentRequest.getLifeTime());

        equipment.setEquipmentStatus(EquipmentStatus.UPDATED);
        equipment.setUpdateAt(LocalDateTime.now());

        EquipmentProfile save = equipmentProfileRepo.save(equipment);

        return mapper.convertValue(save, EquipmentResponse.class);
    }

    @Override
    public void deleteEquipment(Long id) {
        EquipmentProfile equipment = getEquipmentById(id);
        equipment.setEquipmentStatus(EquipmentStatus.DELETED);
        equipment.setUpdateAt(LocalDateTime.now());

        equipmentProfileRepo.save(equipment);

    }

    @Override
    public EquipmentResponse addEquipmentToDepartment(Long equipmentId, Long departmentId) {

        EquipmentProfile equipmentProfile = getEquipmentById(equipmentId);
        DepartmentProfile departmentProfile = departmentService.getDepartmentProfile(departmentId);

        departmentProfile.getEquipmentProfiles().add(equipmentProfile);
        departmentService.updateEquipmentList(departmentProfile);

        equipmentProfile.setDepartmentProfile(departmentProfile);
        EquipmentProfile save = equipmentProfileRepo.save(equipmentProfile);

        EquipmentResponse equipmentResponse = mapper.convertValue(save, EquipmentResponse.class);
        DepartmentResponse department = mapper.convertValue(departmentProfile, DepartmentResponse.class);
        equipmentResponse.setDepartmentResponse(department);

        return equipmentResponse;
    }

    @Override
    public Page<EquipmentResponse> getAllEquipments(Integer page, Integer perPage, String sort, Sort.Direction order, String filter) {
        Pageable pageRequest = getPageRequest(page, perPage, sort, order);

        Page<EquipmentProfile> pageList = equipmentProfileRepo.findAllNotDeleted(pageRequest);
        List<EquipmentResponse> responses = pageList.getContent().stream()
                .map(c -> mapper.convertValue(c, EquipmentResponse.class))
                .collect(Collectors.toList());
        return new PageImpl<>(responses);
    }
}
