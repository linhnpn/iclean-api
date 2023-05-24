package com.iclean.icleanapi.service.impl;

import com.iclean.icleanapi.domain.Job;
import com.iclean.icleanapi.domain.Order;
import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.repository.OrderMapper;
import com.iclean.icleanapi.service.interf.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ResponseEntity<ResponseObject> getFeedback() {

        try{
            List<Order> feedback = orderMapper.getFeedback();
            if (feedback == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Job list is empty.", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.toString(), "Feedback list!", feedback));
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> changeStatusOrder(int orderId, int statusId) {
        try{
            Order order = orderMapper.getOrderById(orderId);
            if (order == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Change status fail!", null));
            }
            boolean checkChange = orderMapper.changeStatusOrder(orderId, statusId);
            if (checkChange){
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject(HttpStatus.OK.toString(), "Change status success!", order));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Change status fail!", null));
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }


}
