package com.iclean.icleanapi.service.interf;

import com.iclean.icleanapi.dto.FeedbackRequest;
import com.iclean.icleanapi.dto.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface FeedbackService {
    ResponseEntity<ResponseObject> getAllFeedback(int jobId, int employeeId, double rate);
    ResponseEntity<ResponseObject> createFeedback(FeedbackRequest feedbackRequest);
}
