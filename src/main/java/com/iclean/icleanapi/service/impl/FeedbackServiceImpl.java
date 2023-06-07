package com.iclean.icleanapi.service.impl;

import com.iclean.icleanapi.domain.Order;
import com.iclean.icleanapi.dto.Feedback;
import com.iclean.icleanapi.dto.FeedbackForm;
import com.iclean.icleanapi.dto.FeedbackRequest;
import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.repository.FeedbackMapper;
import com.iclean.icleanapi.service.interf.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;
    @Override
    public ResponseEntity<ResponseObject> getAllFeedback(int jobId, int employeeId, double rate) {
        try {
            FeedbackForm form = new FeedbackForm();
            form.setJobId(jobId);
            form.setEmployeeId(employeeId);
            form.setRate(rate);
            List<Feedback> feedback = feedbackMapper.getFeedback(form);
            if (feedback == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Feedback list is empty.", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.toString(), "Feedback list!", feedback));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> createFeedback(FeedbackRequest feedbackRequest) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String formatDateTime = now.format(formatter);
            feedbackRequest.setFeedbackTime(LocalDateTime.parse(formatDateTime, formatter));
            boolean check = feedbackMapper.createFeedback(feedbackRequest);
            if (check) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(HttpStatus.OK.toString(), "Create Feedback OK", null));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Create Feedback Failed!", null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }
}
