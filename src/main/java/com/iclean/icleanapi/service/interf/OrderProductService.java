package com.iclean.icleanapi.service.interf;

import com.iclean.icleanapi.dto.NewOrderForm;
import com.iclean.icleanapi.dto.NewOrderProductForm;
import com.iclean.icleanapi.dto.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface OrderProductService {
    ResponseEntity<ResponseObject> changeStatusOrder(int orderId, int statusId);

    ResponseEntity<ResponseObject> createOrderProduct(NewOrderProductForm form);

    ResponseEntity<ResponseObject> getOrderProduct(Integer userId, String category, Integer status);
}
