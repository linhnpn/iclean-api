package com.iclean.icleanapi.service.impl;

import com.iclean.icleanapi.dto.MoneyRequest;
import com.iclean.icleanapi.dto.MoneyResponse;
import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.repository.*;
import com.iclean.icleanapi.service.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private MoneyMapper moneyMapper;

    @Override
    public ResponseEntity<ResponseObject> getMoney(int userId) {
        try {
            MoneyResponse moneyResponse = moneyMapper.getMoneyByUserId(userId);
            if (moneyResponse == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Error.", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.toString(), "moneyResponse!", moneyResponse));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> updateMoney(MoneyRequest moneyRequest) {
        try {
            boolean check = moneyMapper.updateMoney(moneyRequest);
            if (!check) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Error.", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.toString(), "update Success!", null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }
}
