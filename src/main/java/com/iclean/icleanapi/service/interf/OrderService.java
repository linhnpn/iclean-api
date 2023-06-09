package com.iclean.icleanapi.service.interf;

import com.iclean.icleanapi.dto.NewOrderForm;
import com.iclean.icleanapi.dto.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity<ResponseObject> getFeedback();

    ResponseEntity<ResponseObject> changeStatusOrder(int orderId, int statusId);

    ResponseEntity<ResponseObject> createOrder(NewOrderForm form);

    ResponseEntity<ResponseObject> getOrder(Integer userId, Integer empuserloyeeId, Integer status);
}
