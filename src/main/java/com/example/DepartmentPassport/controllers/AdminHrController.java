package com.example.DepartmentPassport.controllers;

import com.example.DepartmentPassport.model.dto.AdminHrRequest;
import com.example.DepartmentPassport.model.dto.AdminHrResponse;
import com.example.DepartmentPassport.service.AdminHrService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//@Tag(name="adminHr")
@RestController
@RequestMapping("/adminHr")
@RequiredArgsConstructor
public class AdminHrController {
    private final AdminHrService adminHrService;


    @PostMapping
//    @Operation (summary = "create AdminHr")
    public AdminHrResponse createAdminHr(@RequestBody AdminHrRequest adminHrRequest) {

        return adminHrService.createAdminHr(adminHrRequest);
    }

    @GetMapping("/{id}")
//    @Operation (summary = "get AdminHr")
    public AdminHrResponse readAdminHr(@PathVariable Long id) {

        return adminHrService.getAdminHr(id);
    }

    @PutMapping("/{id}")
//    @Operation (summary = "update AdminHr")
    public AdminHrResponse updateAdminHr(@PathVariable Long id, @RequestBody AdminHrRequest adminHrRequest) {
        return adminHrService.updateAdminHr(id, adminHrRequest);
    }

    @DeleteMapping
//    @Operation (summary = "delete AdminHr")
    public void deleteAdminHr(@PathVariable Long id) {

        adminHrService.deleteAdminHr(id);
    }
}
