package com.iclean.icleanapi.controller;

import com.iclean.icleanapi.dao.UserMapper;
import com.iclean.icleanapi.domain.User;
import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.service.interf.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private final ProductService productService;
    private final UserMapper userMapper;
    @GetMapping("/{id}")
    public User getProductById(@PathVariable int id) {
        return userMapper.findUserByUserName("user_123");
    }
}
