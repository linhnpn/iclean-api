package com.iclean.icleanapi.repository;

import com.iclean.icleanapi.dto.NewOrderProduct;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemMapper {
    void createOrderItem(NewOrderProduct form);
}
