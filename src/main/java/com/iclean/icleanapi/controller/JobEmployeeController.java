package com.iclean.icleanapi.controller;

import com.iclean.icleanapi.dto.EmployeeJobNewRequest;
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

    @GetMapping("/top-employee")
    public ResponseEntity<ResponseObject> getTopEmployee() {
        return jobEmployeeService.getTopEmployee();
    }

    @PostMapping("")
    public ResponseEntity<ResponseObject> createNewJobforEmployee(@RequestBody EmployeeJobNewRequest request) {
        return jobEmployeeService.createNewJob(request);
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseObject> deleteJob(@RequestParam int job_id, @RequestParam int employee_id) {
        return jobEmployeeService.deleteJob(job_id, employee_id);
    }

    @PutMapping("")
    public ResponseEntity<ResponseObject> updateJob(@RequestBody EmployeeJobNewRequest request) {
        return jobEmployeeService.updateJob(request);
    }
}
