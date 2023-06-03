package com.iclean.icleanapi.repository;

import com.iclean.icleanapi.domain.Order;
import com.iclean.icleanapi.dto.NewOrderForm;
import com.iclean.icleanapi.dto.OrderRequestView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<Order> getFeedback();

    Order getOrderById(int orderId);

    boolean changeStatusOrder(int statusId, Order order);

    boolean createOrder(Order order);

    List<Order> getOrder(OrderRequestView orderRequestView);
}
