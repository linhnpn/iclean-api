package com.iclean.icleanapi.controller;

import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.service.interf.JobEmployeeService;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3030", "https://clatt-api.monoinfinity.net",
        "https://cleaning-house-service.vercel.app", "http://localhost:8080"}, allowCredentials = "true")
@RequestMapping("/api/v1/job-employee")
public class JobEmployeeController {

    @Autowired
    private JobEmployeeService jobEmployeeService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllJob(@RequestParam int jobId) {
        return jobEmployeeService.getEmployeeByJobId(jobId);
    }
}
