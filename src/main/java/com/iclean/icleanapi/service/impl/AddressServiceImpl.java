package com.iclean.icleanapi.service.impl;

import com.iclean.icleanapi.constant.DefaultOrNot;
import com.iclean.icleanapi.domain.Address;
import com.iclean.icleanapi.domain.JobEmployee;
import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.repository.AddressMapper;
import com.iclean.icleanapi.service.interf.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public ResponseEntity<ResponseObject> insertUserAddress(Address address) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String formatDateTime = now.format(formatter);

            address.setTimestamp(LocalDateTime.parse(formatDateTime, formatter));
            boolean check = addressMapper.insertUserAddress(address);
            if (!check){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Insert address fail!", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.toString(), "Insert address done!", address));
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> setDefault(int userId, int addressId) {
        try {
            Address address = addressMapper.getAddressDefaultByUserId(userId);
            if (address != null) {
                address.setIsDefault(DefaultOrNot.NOT_DEFAULT.getValue());
                addressMapper.updateUserAddress(address);
            }
            Address addressDefault = addressMapper.getAddressById(addressId);
            addressDefault.setIsDefault(DefaultOrNot.DEFAULT.getValue());
            boolean check = addressMapper.updateUserAddress(address);
            if (!check) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Set Default user's address fail!", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.toString(), "Set Default user's address done!", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> deleteUserAddress(int userId) {
        try{
            Address existAddressUser = addressMapper.getAddressByUserId(userId);
            if (existAddressUser == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "User's address doesn't exist", null));
            }
            boolean check = addressMapper.deleteUserAddress(userId);
            if (!check){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Delete user's address fail!", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.toString(), "Delete user's address done!", null));
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> updateUserAddress(Address address) {
        try{
            Address existAddressUser = addressMapper.getAddressByUserId(address.getUserId());
            if (existAddressUser == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "User's address doesn't exist", null));
            }
            boolean check = addressMapper.updateUserAddress(address);
            if (!check){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Update user's address fail!", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.toString(), "Update user's address done!", address));
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }
}
