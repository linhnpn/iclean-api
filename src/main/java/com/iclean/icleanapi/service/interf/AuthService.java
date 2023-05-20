package com.iclean.icleanapi.service.interf;

import com.iclean.icleanapi.dto.LoginForm;
import com.iclean.icleanapi.dto.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface AuthService {
    ResponseEntity<ResponseObject> login(LoginForm form);
}
