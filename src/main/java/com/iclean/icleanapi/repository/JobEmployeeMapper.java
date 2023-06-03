package com.iclean.icleanapi.repository;

import com.iclean.icleanapi.domain.JobEmployee;
import com.iclean.icleanapi.domain.User;
import com.iclean.icleanapi.dto.DeleteJobRequest;
import com.iclean.icleanapi.dto.EmployeeJobNewRequest;
import com.iclean.icleanapi.dto.EmployeeJobResponse;
import com.iclean.icleanapi.dto.FeedbackForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JobEmployeeMapper {
    List<EmployeeJobResponse> getEmployeeByJobId(int jobId);
    List<EmployeeJobResponse> getJobByEmployeeId(int employeeId);
    List<User> getTopEmployee();
    boolean createNewJob(JobEmployee jobEmployee);
    boolean deleteJob(DeleteJobRequest request);
    boolean updateJob(EmployeeJobNewRequest request);
}
