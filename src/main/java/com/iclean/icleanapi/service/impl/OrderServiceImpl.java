package com.iclean.icleanapi.service.impl;

import com.iclean.icleanapi.constant.StatusOrder;
import com.iclean.icleanapi.domain.Order;
import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.repository.OrderMapper;
import com.iclean.icleanapi.service.interf.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ResponseEntity<ResponseObject> getFeedback() {

        try {
            List<Order> feedback = orderMapper.getFeedback();
            if (feedback == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Job list is empty.", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.toString(), "Feedback list!", feedback));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> changeStatusOrder(int orderId, int statusId) {
        try {
            //Get LocalDateTime.now() theo format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String formatDateTime = now.format(formatter);

            Order existOrder = orderMapper.getOrderById(orderId);
            existOrder.setStatusId(statusId);

            if (existOrder == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Change status fail!", null));
            } else {
                if (existOrder.getStatusId().equals(StatusOrder.IN_PROCESS.getValue())) {
                    //set workStart = now
                    existOrder.setWorkStart(LocalDateTime.parse(formatDateTime, formatter));
                } else if (existOrder.getStatusId().equals(StatusOrder.DONE.getValue())) {
                    if (!existOrder.getWorkStart().plusHours(existOrder.getWorkTime()).isBefore(LocalDateTime.now())) {
                        return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(HttpStatus.FAILED_DEPENDENCY.toString(), "Cannot done before workDate!", null));
                    }
                    //set workEnd = now
                    existOrder.setWorkEnd(LocalDateTime.parse(formatDateTime, formatter));
                }
                boolean checkChange = orderMapper.changeStatusOrder(orderId, existOrder);
                if (checkChange) {
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseObject(HttpStatus.OK.toString(), "Change status success!", existOrder));
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Change status fail!", null));
            }
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }


}
