package com.iclean.icleanapi.dao;

import com.iclean.icleanapi.domain.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    Product getProductByID(int id);
}
