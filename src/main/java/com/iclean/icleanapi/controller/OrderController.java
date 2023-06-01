package com.iclean.icleanapi.controller;

import com.iclean.icleanapi.dto.ChangeStatusOrderForm;
import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.service.interf.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3030", "https://clatt-api.monoinfinity.net",
        "https://cleaning-house-service.vercel.app", "http://localhost:8080"}, allowCredentials = "true")
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/feedback")
    public ResponseEntity<ResponseObject> getFeedback() {
        return orderService.getFeedback();
    }

    @PutMapping("/status")
    public ResponseEntity<ResponseObject> changeStatus(@RequestBody ChangeStatusOrderForm orderForm) {
        return orderService.changeStatusOrder(orderForm.getOrder_id(), orderForm.getStatus_id());
    }
}
