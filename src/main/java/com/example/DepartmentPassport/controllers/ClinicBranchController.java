package com.example.DepartmentPassport.controllers;

import com.example.DepartmentPassport.model.dto.ClinicBranchRequest;
import com.example.DepartmentPassport.model.dto.ClinicBranchResponse;
import com.example.DepartmentPassport.service.ClinicBranchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@Tag(name="clinicBranch")
@RestController
@RequestMapping("/clinicBranch")
@RequiredArgsConstructor
public class ClinicBranchController {
    private final ClinicBranchService clinicBranchService;

    @PostMapping
    @Operation (summary = "create ClinicBranch")
    public ClinicBranchResponse createClinicBranch(@RequestBody ClinicBranchRequest clinicBranchRequest) {

        return clinicBranchService.createClinicBranch(clinicBranchRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get ClinicBranch")
    public ClinicBranchResponse readClinicBranch(@PathVariable Long id) {

        return clinicBranchService.getClinicBranch(id);
    }

    @PutMapping("/{id}")
    @Operation (summary = "update ClinicBranch")
    public ClinicBranchResponse updateClinicBranch(@PathVariable Long id, @RequestBody ClinicBranchRequest clinicBranchRequest) {
        return clinicBranchService.updateClinicBranch(id, clinicBranchRequest);
    }

    @DeleteMapping("/{id}")
    @Operation (summary = "delete ClinicBranch")
    public void deleteClinicBranch(@PathVariable Long id) {

        clinicBranchService.deleteClinicBranch(id);
    }
    @GetMapping("/allBranches")
    @Operation (summary = "get all branches")
    public Page<ClinicBranchResponse> getAllClinicBranches (@RequestParam (defaultValue = "1") Integer page,
                                        @RequestParam (defaultValue = "10") Integer perPage,
                                        @RequestParam (defaultValue = "fullName") String sort,
                                        @RequestParam (defaultValue = "ASC") Sort.Direction order,
                                        @RequestParam (required = false) String filter
    ) {

        return clinicBranchService.getAllClinicBranches (page,perPage,sort,order,filter);
    }

    @PostMapping("/addBranchToClinic/{branchId}/{clinicId}")
    @Operation (summary = "add branch to clinic")
    public ClinicBranchResponse addBranchToClinic (@PathVariable Long branchId, @PathVariable Long clinicId) {
        return clinicBranchService.addBranchToClinic(branchId, clinicId);
    }
}
