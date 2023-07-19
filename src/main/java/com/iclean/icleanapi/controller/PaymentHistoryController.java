package com.iclean.icleanapi.controller;

import com.iclean.icleanapi.dto.AddNewPaymentRequest;
import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.service.interf.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@CrossOrigin(origins = {"http://localhost:3000", "https://clatt-api.monoinfinity.net",
        "https://iclean-admin.vercel.app", "http://localhost:8080"}, allowCredentials = "true")
public class PaymentHistoryController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllPaymentHistory() {
        return paymentService.GetAllPayment();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseObject> getProductByID(@PathVariable Integer id) {
        return paymentService.GetAllPaymentByUserId(id);
    }

    @GetMapping("/statistic")
    public ResponseEntity<ResponseObject> getStatistic() {
        return paymentService.GetStatistic();
    }

    @PostMapping("")
    public ResponseEntity<ResponseObject> getVideoProductBought(@RequestBody AddNewPaymentRequest request) {
        return paymentService.AddNewPayment(request);
    }
}
