package com.iclean.icleanapi.service.interf;

import com.iclean.icleanapi.dto.MoneyRequest;
import com.iclean.icleanapi.dto.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<ResponseObject> getMoney(int userId);

    ResponseEntity<ResponseObject> updateMoney(MoneyRequest moneyRequest);

    ResponseEntity<ResponseObject> getAllUser();
}
