package com.example.DepartmentPassport.controllers;


import com.example.DepartmentPassport.model.dto.ClinicHrRequest;
import com.example.DepartmentPassport.model.dto.ClinicHrResponse;
import com.example.DepartmentPassport.service.ClinicHrService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


//@Tag(name="ClinicHr")
@RestController
@RequestMapping("/ClinicHr")
@RequiredArgsConstructor
public class ClinicHrController {
    private final ClinicHrService clinicHrService;


    @PostMapping
//    @Operation (summary = "create ClinicHr")
    public ClinicHrResponse createClinicHr(@RequestBody ClinicHrRequest clinicHrRequest) {

        return clinicHrService.createClinicHr(clinicHrRequest);
    }

    @GetMapping("/{id}")
//    @Operation (summary = "get ClinicHr")
    public ClinicHrResponse readClinicHr(@PathVariable Long id) {

        return clinicHrService.getClinicHr(id);
    }

    @PutMapping("/{id}")
//    @Operation (summary = "update ClinicHr")
    public ClinicHrResponse updateClinicHr(@PathVariable Long id, @RequestBody ClinicHrRequest clinicHrRequest) {
        return clinicHrService.updateClinicHr(id, clinicHrRequest);
    }

    @DeleteMapping
//    @Operation (summary = "delete ClinicHr")
    public void deleteClinicHr(@PathVariable Long id) {

        clinicHrService.deleteClinicHr(id);
    }
}
