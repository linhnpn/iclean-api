package com.iclean.icleanapi.controller;

import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.service.interf.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/not-buy")
    public ResponseEntity<ResponseObject> getVideoProduct(@RequestParam(required = false) Integer userId,
                                                   @RequestParam(required = false) String search,
                                                   @RequestParam String category) {
        if (search == null || search.isEmpty()) {
            search = "";
        }
        search = "%" + search + "%";
        return productService.getVideoProduct(userId, search, category);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseObject> getProductByID(@PathVariable Integer id) {
        return productService.getProductByID(id);
    }

    @GetMapping("/video-bought")
    public ResponseEntity<ResponseObject> getVideoProductBought(@RequestParam Integer userId,
                                                                @RequestParam String category) {
        return productService.getVideoProductBought(userId, category);
    }

    @GetMapping("/items")
    public ResponseEntity<ResponseObject> getVideoProductBought(@RequestParam(required = false) String search,
                                                                @RequestParam(required = false) String category) {
        if (search == null || search.isEmpty()) {
            search = "%%";
        }
        search = "%" + search + "%";
        return productService.getItemProduct(search, category);
    }
}
