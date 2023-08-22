package com.example.DepartmentPassport.controllers;

import com.example.DepartmentPassport.model.dto.AdminHrRequest;
import com.example.DepartmentPassport.model.dto.AdminHrResponse;
import com.example.DepartmentPassport.service.AdminHrService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@Tag(name = "adminHr")
@RestController
@RequestMapping("/adminHr")
@RequiredArgsConstructor
public class AdminHrController {
    private final AdminHrService adminHrService;


    @PostMapping("/AdminHr")
    @Operation(summary = "create AdminHr")
    public AdminHrResponse createAdminHr(@RequestBody AdminHrRequest adminHrRequest) {

        return adminHrService.createAdminHr(adminHrRequest);
    }

    @GetMapping("/AdminHr/{id}")
    @Operation (summary = "get AdminHr")
    public AdminHrResponse readAdminHr(@PathVariable Long id) {

        return adminHrService.getAdminHr(id);
    }

    @PutMapping("/AdminHr/{id}")
    @Operation (summary = "update AdminHr")
    public AdminHrResponse updateAdminHr(@PathVariable Long id, @RequestBody AdminHrRequest adminHrRequest) {
        return adminHrService.updateAdminHr(id, adminHrRequest);
    }

    @DeleteMapping("/AdminHr/{id}")
    @Operation (summary = "delete AdminHr")
    public void deleteAdminHr(@PathVariable Long id) {

        adminHrService.deleteAdminHr(id);
    }

    @GetMapping("/AdminHr/allAdminHrs")
    @Operation (summary = "get all adminHr")
    public Page<AdminHrResponse> getAllAdminHrs (@RequestParam (defaultValue = "1") Integer page,
                                                          @RequestParam (defaultValue = "10") Integer perPage,
                                                          @RequestParam (defaultValue = "jobTitle") String sort,
                                                          @RequestParam (defaultValue = "ASC") Sort.Direction order,
                                                          @RequestParam (required = false) String filter
    ) {

        return adminHrService.getAllAdminHrs (page,perPage,sort,order,filter);
    }

    @PostMapping("/AdminHr/addAdminHrToDepartment/{adminHrId}/{departmentId}")
    @Operation (summary = "add adminHr to department")
    public AdminHrResponse addAdminHrToDepartment (@PathVariable Long adminHrId, @PathVariable Long departmentId) {
        return adminHrService.addAdminHrToDepartment(adminHrId, departmentId);
    }
}
