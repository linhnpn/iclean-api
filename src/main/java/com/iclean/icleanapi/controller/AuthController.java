package com.iclean.icleanapi.controller;

import com.iclean.icleanapi.dto.ChangePasswordForm;
import com.iclean.icleanapi.dto.LoginForm;
import com.iclean.icleanapi.dto.NewAccountForm;
import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.service.interf.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3030", "https://clatt-api.monoinfinity.net",
        "https://cleaning-house-service.vercel.app", "http://localhost:8080"}, allowCredentials = "true")
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<ResponseObject> login(@RequestBody LoginForm form) {
        return authService.login(form);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseObject> createAccount(
            @RequestBody NewAccountForm form) {
        return authService.createAccount(form);
    }

    @PutMapping("/password")
    public ResponseEntity<ResponseObject> changePassword(
            @RequestBody ChangePasswordForm form) {
        return authService.changePassword(form);
    }
}
