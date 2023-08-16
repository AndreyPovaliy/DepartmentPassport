package com.example.DepartmentPassport.controllers;

import com.example.DepartmentPassport.model.dto.DepartmentRequest;
import com.example.DepartmentPassport.model.dto.DepartmentResponse;
import com.example.DepartmentPassport.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@Tag(name="department")
@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;


    @PostMapping
    @Operation(summary = "create Department")
    public DepartmentResponse createDepartment(@RequestBody DepartmentRequest departmentRequest) {

        return departmentService.createDepartment(departmentRequest);
    }


    @GetMapping("/{id}")
    @Operation (summary = "get Department")
    public DepartmentResponse readDepartment(@PathVariable Long id) {

        return departmentService.getDepartment(id);
    }

    @PutMapping("/{id}")
    @Operation (summary = "update Department")
    public DepartmentResponse updateDepartment(@PathVariable Long id, @RequestBody DepartmentRequest departmentRequest) {
        return departmentService.updateDepartment(id, departmentRequest);
    }

    @DeleteMapping("/{id}")
    @Operation (summary = "delete Department")
    public void deleteDepartment(@PathVariable Long id) {

        departmentService.deleteDepartment(id);
    }

    @GetMapping("/allDepartments")
    @Operation (summary = "get all departments")
    public Page<DepartmentResponse> getAllDepartments (@RequestParam (defaultValue = "1") Integer page,
                                                            @RequestParam (defaultValue = "10") Integer perPage,
                                                            @RequestParam (defaultValue = "name") String sort,
                                                            @RequestParam (defaultValue = "ASC") Sort.Direction order,
                                                            @RequestParam (required = false) String filter
    ) {

        return departmentService.getAllDepartments (page,perPage,sort,order,filter);
    }

    @PostMapping("/addDepartmentToBranch{departmentId}/{branchId}")
    @Operation (summary = "add department to branch")
    public DepartmentResponse addDepartmentToBranch (@PathVariable Long departmentId, @PathVariable Long branchId) {
        return departmentService.addDepartmentToBranch(departmentId, branchId);
    }
}
