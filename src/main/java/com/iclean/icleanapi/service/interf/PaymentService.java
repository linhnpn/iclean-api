package com.iclean.icleanapi.service.interf;

import com.iclean.icleanapi.dto.AddNewPaymentRequest;
import com.iclean.icleanapi.dto.ResponseObject;
import org.springframework.http.ResponseEntity;
public interface PaymentService {
    ResponseEntity<ResponseObject> AddNewPayment(AddNewPaymentRequest request);
    ResponseEntity<ResponseObject> GetAllPayment();
    ResponseEntity<ResponseObject> GetStatistic();
    ResponseEntity<ResponseObject> GetAllPaymentByUserId(int userId);
}
