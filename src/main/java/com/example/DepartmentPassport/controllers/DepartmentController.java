package com.example.DepartmentPassport.controllers;

import com.example.DepartmentPassport.model.dto.DepartmentRequest;
import com.example.DepartmentPassport.model.dto.DepartmentResponse;
import com.example.DepartmentPassport.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//@Tag(name="ClinicHr")
@RestController
@RequestMapping("/ClinicHr")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;


    @PostMapping
//    @Operation (summary = "create Department")
    public DepartmentResponse createDepartment(@RequestBody DepartmentRequest departmentRequest) {

        return departmentService.createDepartment(departmentRequest);
    }

    @GetMapping("/{id}")
//    @Operation (summary = "get Department")
    public DepartmentResponse readDepartment(@PathVariable Long id) {

        return departmentService.getDepartment(id);
    }

    @PutMapping("/{id}")
//    @Operation (summary = "update Department")
    public DepartmentResponse updateDepartment(@PathVariable Long id, @RequestBody DepartmentRequest departmentRequest) {
        return departmentService.updateDepartment(id, departmentRequest);
    }

    @DeleteMapping
//    @Operation (summary = "delete Department")
    public void deleteDepartment(@PathVariable Long id) {

        departmentService.deleteDepartment(id);
    }
}
