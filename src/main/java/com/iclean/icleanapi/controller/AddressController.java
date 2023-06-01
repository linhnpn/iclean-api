package com.iclean.icleanapi.controller;

import com.iclean.icleanapi.domain.Address;
import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.service.interf.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("")
    public ResponseEntity<ResponseObject> insertUserAddress(@RequestBody Address address) {
        return addressService.insertUserAddress(address);
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseObject> deleteUserAddress(@RequestParam int userId) {
        return addressService.deleteUserAddress(userId);
    }

    @PutMapping("")
    public ResponseEntity<ResponseObject> updateUserAddress(@RequestBody Address address) {
        return addressService.updateUserAddress(address);
    }

    @PutMapping("/default-address")
    public ResponseEntity<ResponseObject> updateDefaultUserAddress(@RequestParam int userId, @RequestParam int addressId) {
        return addressService.setDefault(userId, addressId);
    }
}
