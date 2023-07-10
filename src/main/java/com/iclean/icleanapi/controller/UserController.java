package com.iclean.icleanapi.controller;

import com.iclean.icleanapi.dto.*;
import com.iclean.icleanapi.service.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/money")
    public ResponseEntity<ResponseObject> getMoney(@RequestParam Integer userId) {
        return userService.getMoney(userId);
    }

    @PostMapping("/money")
    public ResponseEntity<ResponseObject> updateMoney(
            @RequestBody MoneyRequest form) {
        return userService.updateMoney(form);
    }
}
