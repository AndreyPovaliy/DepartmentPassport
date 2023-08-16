package com.example.DepartmentPassport.controllers;



import com.example.DepartmentPassport.model.dto.ClinicHrRequest;
import com.example.DepartmentPassport.model.dto.ClinicHrResponse;
import com.example.DepartmentPassport.service.ClinicHrService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@Tag(name="clinicHr")
@RestController
@RequestMapping("/clinicHr")
@RequiredArgsConstructor
public class ClinicHrController {
    private final ClinicHrService clinicHrService;


    @PostMapping
    @Operation (summary = "create ClinicHr")
    public ClinicHrResponse createClinicHr(@RequestBody ClinicHrRequest clinicHrRequest) {

        return clinicHrService.createClinicHr(clinicHrRequest);
    }

    @GetMapping("/{id}")
    @Operation (summary = "get ClinicHr")
    public ClinicHrResponse readClinicHr(@PathVariable Long id) {

        return clinicHrService.getClinicHr(id);
    }

    @PutMapping("/{id}")
    @Operation (summary = "update ClinicHr")
    public ClinicHrResponse updateClinicHr(@PathVariable Long id, @RequestBody ClinicHrRequest clinicHrRequest) {
        return clinicHrService.updateClinicHr(id, clinicHrRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete ClinicHr")
    public void deleteClinicHr(@PathVariable Long id) {

        clinicHrService.deleteClinicHr(id);
    }

    @GetMapping("/allClinicHrs")
    @Operation (summary = "get all clinicHr")
    public Page<ClinicHrResponse> getAllClinicHrs (@RequestParam (defaultValue = "1") Integer page,
                                                 @RequestParam (defaultValue = "10") Integer perPage,
                                                 @RequestParam (defaultValue = "lastName") String sort,
                                                 @RequestParam (defaultValue = "ASC") Sort.Direction order,
                                                 @RequestParam (required = false) String filter
    ) {

        return clinicHrService.getAllClinicHrs (page,perPage,sort,order,filter);
    }

    @PostMapping("/addClinicHrToDepartment/{clinicHrId}/{departmentId}")
    @Operation (summary = "add clinicHr to {department")
    public ClinicHrResponse addClinicHrToDepartment (@PathVariable Long clinicHrId, @PathVariable Long departmentId) {
        return clinicHrService.addClinicHrToDepartment(clinicHrId, departmentId);
    }
}
