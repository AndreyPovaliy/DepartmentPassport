package com.example.DepartmentPassport.controllers;

import com.example.DepartmentPassport.model.dto.MedicalDeviceResponse;
import com.example.DepartmentPassport.model.dto.MedicalDeviceRequest;
import com.example.DepartmentPassport.service.MedicalDeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@Tag(name="medicalDevice")
@RestController
@RequestMapping("/medicalDevice")
@RequiredArgsConstructor
public class MedicalDeviceController {
    private final MedicalDeviceService medicalDeviceService;


    @PostMapping
    @Operation(summary = "create MedicalDevice")
    public MedicalDeviceResponse createMedicalDevice(@RequestBody MedicalDeviceRequest medicalDeviceRequest) {

        return medicalDeviceService.createMedicalDevice(medicalDeviceRequest);
    }

    @GetMapping("/{id}")
    @Operation (summary = "get MedicalDevice")
    public MedicalDeviceResponse readMedicalDevice(@PathVariable Long id) {

        return medicalDeviceService.getMedicalDevice(id);
    }

    @PutMapping("/{id}")
    @Operation (summary = "update MedicalDevice")
    public MedicalDeviceResponse updateMedicalDevice(@PathVariable Long id, @RequestBody MedicalDeviceRequest medicalDeviceRequest) {
        return medicalDeviceService.updateMedicalDevice(id, medicalDeviceRequest);
    }

    @DeleteMapping("/{id}")
    @Operation (summary = "delete MedicalDevice")
    public void deleteMedicalDevice(@PathVariable Long id) {

        medicalDeviceService.deleteMedicalDevice(id);
    }

    @GetMapping("/allMedicalDevices")
    @Operation (summary = "get all MedicalDevice")
    public Page<MedicalDeviceResponse> getAllMedicalDevices (@RequestParam (defaultValue = "1") Integer page,
                                                     @RequestParam (defaultValue = "10") Integer perPage,
                                                     @RequestParam (defaultValue = "name") String sort,
                                                     @RequestParam (defaultValue = "ASC") Sort.Direction order,
                                                     @RequestParam (required = false) String filter
    ) {

        return medicalDeviceService.getAllMedicalDevices (page,perPage,sort,order,filter);
    }

    @PostMapping("/addMedicalDeviceToDepartment/{medicalDeviceId}/{departmentId}")
    @Operation (summary = "add medicalDevice to department")
    public MedicalDeviceResponse addMedicalDeviceToDepartment (@PathVariable Long medicalDeviceId, @PathVariable Long departmentId) {
        return medicalDeviceService.addMedicalDeviceToDepartment(medicalDeviceId, departmentId);
    }
}
