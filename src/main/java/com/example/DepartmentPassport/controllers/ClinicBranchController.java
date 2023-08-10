package com.example.DepartmentPassport.controllers;

import com.example.DepartmentPassport.model.dto.ClinicBranchRequest;
import com.example.DepartmentPassport.model.dto.ClinicBranchResponse;
import com.example.DepartmentPassport.service.ClinicBranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//@Tag(name="clinicBranch")
@RestController
@RequestMapping("/clinicBranch")
@RequiredArgsConstructor
public class ClinicBranchController {
    private final ClinicBranchService clinicBranchService;

    @PostMapping
//    @Operation (summary = "create ClinicBranch")
    public ClinicBranchResponse createClinicBranch(@RequestBody ClinicBranchRequest clinicBranchRequest) {

        return clinicBranchService.createClinicBranch(clinicBranchRequest);
    }

    @GetMapping("/{id}")
//    @Operation (summary = "get ClinicBranch")
    public ClinicBranchResponse readClinicBranch(@PathVariable Long id) {

        return clinicBranchService.getClinicBranch(id);
    }

    @PutMapping("/{id}")
//    @Operation (summary = "update ClinicBranch")
    public ClinicBranchResponse updateClinicBranch(@PathVariable Long id, @RequestBody ClinicBranchRequest clinicBranchRequest) {
        return clinicBranchService.updateClinicBranch(id, clinicBranchRequest);
    }

    @DeleteMapping
//    @Operation (summary = "delete ClinicBranch")
    public void deleteClinicBranch(@PathVariable Long id) {

        clinicBranchService.deleteClinicBranch(id);
    }
}
