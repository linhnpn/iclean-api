package com.iclean.icleanapi.controller;

import com.iclean.icleanapi.dto.*;
import com.iclean.icleanapi.service.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = {"http://localhost:3000", "https://clatt-api.monoinfinity.net",
        "https://iclean-admin.vercel.app", "http://localhost:8080"}, allowCredentials = "true")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/money")
    public ResponseEntity<ResponseObject> getMoney(@RequestParam Integer userId) {
        return userService.getMoney(userId);
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/money")
    public ResponseEntity<ResponseObject> updateMoney(
            @RequestBody MoneyRequest form) {
        return userService.updateMoney(form);
    }
}
