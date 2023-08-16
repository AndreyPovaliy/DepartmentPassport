package com.example.DepartmentPassport.controllers;

import com.example.DepartmentPassport.model.dto.DrugRequest;
import com.example.DepartmentPassport.model.dto.DrugResponse;
import com.example.DepartmentPassport.service.DrugService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@Tag(name="drug")
@RestController
@RequestMapping("/drug")
@RequiredArgsConstructor
public class DrugController {
    private final DrugService drugService;


    @PostMapping
    @Operation(summary = "create Drug")
    public DrugResponse createDrug(@RequestBody DrugRequest drugRequest) {

        return drugService.createDrug(drugRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get Drug")
    public DrugResponse readDrug(@PathVariable Long id) {

        return drugService.getDrug(id);
    }

    @PutMapping("/{id}")
    @Operation (summary = "update Drug")
    public DrugResponse updateDrug(@PathVariable Long id, @RequestBody DrugRequest drugRequest) {
        return drugService.updateDrug(id, drugRequest);
    }

    @DeleteMapping("/{id}")
    @Operation (summary = "delete Drug")
    public void deleteDrug(@PathVariable Long id) {

        drugService.deleteDrug(id);
    }


    @GetMapping("/allDrugs")
    @Operation (summary = "get all Drug")
    public Page<DrugResponse> getAllDrugs (@RequestParam (defaultValue = "1") Integer page,
                                                     @RequestParam (defaultValue = "10") Integer perPage,
                                                     @RequestParam (defaultValue = "tradeName") String sort,
                                                     @RequestParam (defaultValue = "ASC") Sort.Direction order,
                                                     @RequestParam (required = false) String filter
    ) {

        return drugService.getAllDrugs (page,perPage,sort,order,filter);
    }

    @PostMapping("/addDrugToDepartment/{drugId}/{departmentId}")
    @Operation (summary = "add drug to department")
    public DrugResponse addDrugToDepartment (@PathVariable Long drugId, @PathVariable Long departmentId) {
        return drugService.addDrugToDepartment(drugId, departmentId);
    }
}
