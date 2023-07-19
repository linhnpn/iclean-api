package com.iclean.icleanapi.service.impl;

import com.iclean.icleanapi.domain.*;
import com.iclean.icleanapi.dto.AddNewPaymentRequest;
import com.iclean.icleanapi.dto.PaymentHistoryResponse;
import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.dto.StatisticResponse;
import com.iclean.icleanapi.repository.PaymentMapper;
import com.iclean.icleanapi.service.interf.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;
    @Override
    public ResponseEntity<ResponseObject> AddNewPayment(AddNewPaymentRequest request) {
        try {
            PaymentHistory paymentHistory = new PaymentHistory();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String formatDateTime = now.format(formatter);

            paymentHistory.setPaymentDate(LocalDateTime.parse(formatDateTime, formatter));
            paymentHistory.setPaymentCode(request.getCode());
            paymentHistory.setUserId(request.getUserId());
            paymentHistory.setBalance(request.getBalance());

            boolean check = paymentMapper.AddPayment(paymentHistory);
            if (check) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject(HttpStatus.OK.toString(), "Create Payment Successful", null));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Fail when create Payment", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> GetAllPayment() {
        try {
            List<PaymentHistoryResponse> responses = paymentMapper.GetAllPayment();
            if (responses == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Payment History is empty.", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.toString(), "Payment History list!", responses));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> GetStatistic() {
        try {
            List<StatisticResponse> responses = paymentMapper.GetStatistic();
            if (responses == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Payment History is empty.", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.toString(), "Payment History list!", responses));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> GetAllPaymentByUserId(int userId) {
        try {
            List<PaymentHistoryResponse> responses = paymentMapper.GetPaymentByUserID(userId);
            if (responses == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Payment History is empty.", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.toString(), "Payment History list!", responses));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }
}
