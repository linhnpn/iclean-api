package com.iclean.icleanapi.controller;

import com.iclean.icleanapi.dto.ChangeStatusOrderForm;
import com.iclean.icleanapi.dto.NewOrderForm;
import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.service.interf.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("")
    public ResponseEntity<ResponseObject> createOrder(@RequestBody NewOrderForm form) {
        return orderService.createOrder(form);
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getOrder(@RequestParam(required = false) Integer userId,
                                                   @RequestParam(required = false) Integer employeeId,
                                                   @RequestParam(required = false) Integer status) {
        return orderService.getOrder(userId, employeeId, status);
    }

    @GetMapping("/feedback")
    public ResponseEntity<ResponseObject> getFeedback() {
        return orderService.getFeedback();
    }

    @PutMapping("/status")
    public ResponseEntity<ResponseObject> changeStatus(@RequestBody ChangeStatusOrderForm orderForm) {
        return orderService.changeStatusOrder(orderForm.getOrder_id(), orderForm.getStatus_id());
    }
}
