package com.iclean.icleanapi.repository;

import com.iclean.icleanapi.dto.EmployeeJobResponse;
import com.iclean.icleanapi.dto.FeedbackForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JobMapper {
    List<FeedbackForm> getAllJob();

    EmployeeJobResponse getJobById(int jobId, int employeeId);
}
