package com.iclean.icleanapi.service.impl;

import com.iclean.icleanapi.domain.Notification;
import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.repository.NotificationMapper;
import com.iclean.icleanapi.service.interf.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public ResponseEntity<ResponseObject> getNotificationByUserId(int userId) {
        try {
            List<Notification> listNotiByUserId = notificationMapper.getNotificationByUserId(userId);
            if (listNotiByUserId.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "User don't have notification", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.toString(), "List notification!", listNotiByUserId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), ex.getMessage(), null));
        }
    }
}
