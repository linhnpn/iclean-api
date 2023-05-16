package com.iclean.icleanapi.service.impl;

import com.iclean.icleanapi.dao.ProductMapper;
import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.service.interf.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;

    @Override
    public ResponseEntity<ResponseObject> getProductByID(int id) {
        return ResponseEntity.status(HttpStatus.OK).
                body(new ResponseObject(HttpStatus.OK.toString(),
                        null, productMapper.getProductByID(id)));
    }
}
