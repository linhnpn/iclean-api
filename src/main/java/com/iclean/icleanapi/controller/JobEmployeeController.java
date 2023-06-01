package com.iclean.icleanapi.controller;

import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.service.interf.JobEmployeeService;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-employee")
public class JobEmployeeController {

    @Autowired
    private JobEmployeeService jobEmployeeService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllJob(@RequestParam int jobId) {
        return jobEmployeeService.getEmployeeByJobId(jobId);
    }

    @GetMapping("/employee")
    public ResponseEntity<ResponseObject> getAllJobByEmpl(@RequestParam int employee_id) {
        return jobEmployeeService.getJobByEmployee(employee_id);
    }
}
