package com.iclean.icleanapi.service.impl;

import com.iclean.icleanapi.constant.StatusOrder;
import com.iclean.icleanapi.domain.Address;
import com.iclean.icleanapi.domain.JobEmployee;
import com.iclean.icleanapi.domain.Order;
import com.iclean.icleanapi.domain.User;
import com.iclean.icleanapi.dto.*;
import com.iclean.icleanapi.repository.FeedbackMapper;
import com.iclean.icleanapi.repository.JobEmployeeMapper;
import com.iclean.icleanapi.service.interf.JobEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class JobEmployeeServiceImpl implements JobEmployeeService {

    @Autowired
    private JobEmployeeMapper jobEmployeeMapper;
    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public ResponseEntity<ResponseObject> getEmployeeByJobId(int jobId) {

        try{
            List<EmployeeJobResponse> emplJob = jobEmployeeMapper.getEmployeeByJobId(jobId);
            if (!emplJob.isEmpty()) {
                for (EmployeeJobResponse employeeJobResponse : emplJob) {
                    FeedbackForm form = new FeedbackForm();
                    form.setEmployeeId(employeeJobResponse.getEmployeeId());
                    form.setJobId(employeeJobResponse.getJobId());
                    int count = feedbackMapper.getFeedback(form).size();
                    employeeJobResponse.setCountRate(count);
                }
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
            List<EmployeeJobResponse> emplJob = jobEmployeeMapper.getJobByEmployeeId(employeeId);
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
    public ResponseEntity<ResponseObject> getTopEmployee() {
        try{
            List<User> employees = jobEmployeeMapper.getTopEmployee();
            if (employees == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Employee list is empty.", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.toString(), "Employee list!", employees));
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> createNewJob(EmployeeJobNewRequest request) {
        try {
            JobEmployee jobEmployee = new JobEmployee();
            jobEmployee.setEmployeeId(request.getEmployeeId());
            jobEmployee.setJobId(request.getJobId());
            jobEmployee.setDescription(request.getDescription());
            jobEmployee.setPrice(request.getPrice());
            jobEmployee.setTimestamp(new Date());
            boolean check = jobEmployeeMapper.createNewJob(jobEmployee);
            if (check) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject(HttpStatus.OK.toString(), "Create New Job success!", null));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Create New Job Failed!", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> deleteJob(int jobId, int employeeId) {
        try {
            DeleteJobRequest request = new DeleteJobRequest(jobId, employeeId);
            boolean check = jobEmployeeMapper.deleteJob(request);
            if (check) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject(HttpStatus.OK.toString(), "Delete Job success!", null));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Delete Job Failed!", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> updateJob(EmployeeJobNewRequest request) {
        try {
            JobEmployee jobEmployee = new JobEmployee();
            jobEmployee.setEmployeeId(request.getEmployeeId());
            jobEmployee.setJobId(request.getJobId());
            jobEmployee.setDescription(request.getDescription());
            jobEmployee.setPrice(request.getPrice());
            jobEmployee.setTimestamp(new Date());
            boolean check = jobEmployeeMapper.updateJob(request);
            if (check) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject(HttpStatus.OK.toString(), "Update Job success!", null));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Update Job Failed!", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), null));
        }
    }
}
