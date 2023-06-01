package com.iclean.icleanapi.service.interf;

import com.iclean.icleanapi.dto.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity<ResponseObject> getFeedback();

    ResponseEntity<ResponseObject> changeStatusOrder(int orderId, int statusId);

}
