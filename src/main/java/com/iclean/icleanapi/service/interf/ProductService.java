package com.iclean.icleanapi.service.interf;

import com.iclean.icleanapi.dto.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<ResponseObject> getProductByID(int id);
}
