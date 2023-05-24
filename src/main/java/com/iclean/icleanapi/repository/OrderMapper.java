package com.iclean.icleanapi.repository;

import com.iclean.icleanapi.domain.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<Order> getFeedback();

    Order getOrderById(int orderId);

    boolean changeStatusOrder(int statusId, int orderId);
}
