package com.iclean.icleanapi.service.interf;


import com.iclean.icleanapi.domain.Job;
import com.iclean.icleanapi.dto.NewAccountForm;
import com.iclean.icleanapi.dto.ResponseObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JobService {

    ResponseEntity<ResponseObject> getAllJob();
}
