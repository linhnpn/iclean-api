package com.iclean.icleanapi.service.impl;

import com.iclean.icleanapi.domain.JobEmployee;
import com.iclean.icleanapi.domain.Order;
import com.iclean.icleanapi.dto.EmployeeJobResponse;
import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.repository.JobEmployeeMapper;
import com.iclean.icleanapi.service.interf.JobEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobEmployeeServiceImpl implements JobEmployeeService {

    @Autowired
    private JobEmployeeMapper jobEmployeeMapper;

    @Override
    public ResponseEntity<ResponseObject> getEmployeeByJobId(int jobId) {

        try{
            List<EmployeeJobResponse> emplJob = jobEmployeeMapper.getEmployeeByJobId(jobId);
            if (emplJob == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Job list is empty.", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.toString(), "Employee list!", emplJob));
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> getJobByEmployee(int employeeId) {
        try{
            List<EmployeeJobResponse> emplJob = jobEmployeeMapper.getEmployeeByJobId(employeeId);
            if (emplJob == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Job list is empty.", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.toString(), "Employee list!", emplJob));
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }
}
