package com.iclean.icleanapi.controller;

import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.service.interf.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllJob() {
        return jobService.getAllJob();
    }
}
