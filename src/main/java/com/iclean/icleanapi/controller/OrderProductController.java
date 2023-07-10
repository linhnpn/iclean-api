package com.iclean.icleanapi.controller;

import com.iclean.icleanapi.dto.ChangeStatusOrderForm;
import com.iclean.icleanapi.dto.NewOrderProductForm;
import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.service.interf.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order-product")
public class OrderProductController {
    @Autowired
    private OrderProductService orderService;

    @PostMapping("")
    public ResponseEntity<ResponseObject> createOrder(@RequestBody NewOrderProductForm form) {
        return orderService.createOrderProduct(form);
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getOrder(@RequestParam(required = false) Integer userId,
                                                   @RequestParam(required = false) String category,
                                                   @RequestParam(required = false) Integer status) {
        return orderService.getOrderProduct(userId, category, status);
    }

    @PutMapping("/status")
    public ResponseEntity<ResponseObject> changeStatus(@RequestBody ChangeStatusOrderForm orderForm) {
        return orderService.changeStatusOrder(orderForm.getOrder_id(), orderForm.getStatus_id());
    }
}
