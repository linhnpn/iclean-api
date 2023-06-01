package com.iclean.icleanapi.service.impl;

import com.iclean.icleanapi.dto.FeedbackForm;
import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.repository.JobMapper;
import com.iclean.icleanapi.service.interf.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobMapper jobMapper;

    @Override
    public ResponseEntity<ResponseObject> getAllJob() {
        try{
            List<FeedbackForm> jobList = jobMapper.getAllJob();
            if (jobList == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Job list is empty.", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.toString(), "Job list!", jobList));
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }
}
