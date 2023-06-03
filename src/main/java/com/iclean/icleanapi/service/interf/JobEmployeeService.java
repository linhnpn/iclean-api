package com.iclean.icleanapi.service.interf;


import com.iclean.icleanapi.dto.EmployeeJobNewRequest;
import com.iclean.icleanapi.dto.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface JobEmployeeService {

    ResponseEntity<ResponseObject> getEmployeeByJobId(int jobId);

    ResponseEntity<ResponseObject> getJobByEmployee(int employeeId);

    ResponseEntity<ResponseObject> getTopEmployee();

    ResponseEntity<ResponseObject> createNewJob(EmployeeJobNewRequest request);

    ResponseEntity<ResponseObject> deleteJob(int jobId, int employeeId);

    ResponseEntity<ResponseObject> updateJob(EmployeeJobNewRequest request);
}
