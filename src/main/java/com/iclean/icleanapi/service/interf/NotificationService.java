package com.iclean.icleanapi.service.interf;

import com.iclean.icleanapi.dto.NewOrderForm;
import com.iclean.icleanapi.dto.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface NotificationService {

    ResponseEntity<ResponseObject> getNotificationByUserId(int userId);
}
