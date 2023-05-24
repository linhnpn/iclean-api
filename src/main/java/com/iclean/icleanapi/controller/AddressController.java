package com.iclean.icleanapi.controller;

import com.iclean.icleanapi.domain.Address;
import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.service.interf.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3030", "https://clatt-api.monoinfinity.net",
        "https://cleaning-house-service.vercel.app", "http://localhost:8080"}, allowCredentials = "true")
@RequestMapping("/api/v1/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertUserAddress(@RequestBody Address address) {
        return addressService.insertUserAddress(address);
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseObject> deleteUserAddress(@RequestParam int userId) {
        return addressService.deleteUserAddress(userId);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseObject> updateUserAddress(@RequestBody Address address) {
        return addressService.updateUserAddress(address);
    }
}
