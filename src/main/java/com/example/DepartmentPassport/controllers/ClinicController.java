package com.example.DepartmentPassport.controllers;

import com.example.DepartmentPassport.model.dto.ClinicRequest;
import com.example.DepartmentPassport.model.dto.ClinicResponse;
import com.example.DepartmentPassport.service.ClinicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="clinic")
@RestController
@RequestMapping("/clinic")
@RequiredArgsConstructor
public class ClinicController {
    private final ClinicService clinicService;


    @PostMapping
    @Operation(summary = "create Clinic")
    public ClinicResponse createClinic(@RequestBody ClinicRequest clinicRequest) {

        return clinicService.createClinic(clinicRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get Clinic")
    public ClinicResponse readClinic(@PathVariable Long id) {

        return clinicService.getClinic(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update Clinic")
    public ClinicResponse updateClinic(@PathVariable Long id, @RequestBody ClinicRequest ClinicRequest) {
        return clinicService.updateClinic(id, ClinicRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete Clinic")
    public void deleteClinic(@PathVariable Long id) {

        clinicService.deleteClinic(id);
    }

    @GetMapping("/allClinics")
    @Operation(summary = "get all clinics")
    public List<ClinicResponse> getAllClinics() {

        return clinicService.getAllClinics();
    }
}
