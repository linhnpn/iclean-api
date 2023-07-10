package com.iclean.icleanapi.repository;

import com.iclean.icleanapi.domain.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    Product getProductByID(int productId);
}
