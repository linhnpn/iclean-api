package com.iclean.icleanapi.controller;

import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.service.interf.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getNotificationByUserId(@RequestParam Integer userId) {
        return notificationService.getNotificationByUserId(userId);
    }

}
