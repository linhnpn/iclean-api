package com.iclean.icleanapi.controller;

import com.iclean.icleanapi.dto.FeedbackRequest;
import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.service.interf.AddressService;
import com.iclean.icleanapi.service.interf.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @GetMapping("/feedback")
    public ResponseEntity<ResponseObject> getFeedback(@RequestParam int job_id, @RequestParam int employee_id) {
        return feedbackService.getAllFeedback(job_id, employee_id);
    }

    @PostMapping("/feedback")
    public ResponseEntity<ResponseObject> createFeedback(@RequestBody FeedbackRequest request) {
        return feedbackService.createFeedback(request);
    }
}
