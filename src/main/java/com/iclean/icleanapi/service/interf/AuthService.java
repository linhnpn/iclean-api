package com.iclean.icleanapi.service.interf;

import com.iclean.icleanapi.dto.ChangePasswordForm;
import com.iclean.icleanapi.dto.LoginForm;
import com.iclean.icleanapi.dto.NewAccountForm;
import com.iclean.icleanapi.dto.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ResponseObject> login(LoginForm form);
    ResponseEntity<ResponseObject> createAccount(NewAccountForm form);
    ResponseEntity<ResponseObject> changePassword(ChangePasswordForm form);
}
