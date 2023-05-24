package com.iclean.icleanapi.service.interf;

import com.iclean.icleanapi.domain.Address;
import com.iclean.icleanapi.dto.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface AddressService {
    ResponseEntity<ResponseObject> insertUserAddress(Address address);

    ResponseEntity<ResponseObject> deleteUserAddress(int userId);

    ResponseEntity<ResponseObject> updateUserAddress(Address address);

}
