package com.iclean.icleanapi.repository;

import com.iclean.icleanapi.domain.Product;
import com.iclean.icleanapi.dto.ProductRequestForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    Product getProductByID(int productId);
    List<Product> getVideoProductNotBuyByUserID(Integer userId, String search, String category);
    List<Product> getVideoProductBoughtByUserID(ProductRequestForm form);
    List<Product> getItemProduct(String search, String category);
}
