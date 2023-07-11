package com.iclean.icleanapi.service.interf;

import com.iclean.icleanapi.dto.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<ResponseObject> getVideoProduct(Integer userId, String search, String category);
    ResponseEntity<ResponseObject> getItemProduct(String search, String category);
    ResponseEntity<ResponseObject> getProductByID(Integer productId);
    ResponseEntity<ResponseObject> getVideoProductBought(Integer userId, String category);
}
