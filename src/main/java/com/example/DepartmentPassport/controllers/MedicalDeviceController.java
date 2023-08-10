package com.example.DepartmentPassport.controllers;

import com.example.DepartmentPassport.model.dto.MedicalDeviceRequest;
import com.example.DepartmentPassport.model.dto.MedicalDeviceResponse;
import com.example.DepartmentPassport.service.MedicalDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//@Tag(name="medicalDevice")
@RestController
@RequestMapping("/medicalDevice")
@RequiredArgsConstructor
public class MedicalDeviceController {
    private final MedicalDeviceService medicalDeviceService;


    @PostMapping
//    @Operation (summary = "create MedicalDevice")
    public MedicalDeviceResponse createMedicalDevice(@RequestBody MedicalDeviceRequest medicalDeviceRequest) {

        return medicalDeviceService.createMedicalDevice(medicalDeviceRequest);
    }

    @GetMapping("/{id}")
//    @Operation (summary = "get MedicalDevice")
    public MedicalDeviceResponse readMedicalDevice(@PathVariable Long id) {

        return medicalDeviceService.getMedicalDevice(id);
    }

    @PutMapping("/{id}")
//    @Operation (summary = "update MedicalDevice")
    public MedicalDeviceResponse updateMedicalDevice(@PathVariable Long id, @RequestBody MedicalDeviceRequest medicalDeviceRequest) {
        return medicalDeviceService.updateMedicalDevice(id, medicalDeviceRequest);
    }

    @DeleteMapping
//    @Operation (summary = "delete MedicalDevice")
    public void deleteMedicalDevice(@PathVariable Long id) {

        medicalDeviceService.deleteMedicalDevice(id);
    }
}
