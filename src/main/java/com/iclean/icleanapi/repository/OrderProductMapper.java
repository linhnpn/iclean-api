package com.iclean.icleanapi.repository;

import com.iclean.icleanapi.domain.Order;
import com.iclean.icleanapi.domain.OrderProduct;
import com.iclean.icleanapi.dto.OrderProductRequestView;
import com.iclean.icleanapi.dto.OrderRequestView;
import com.iclean.icleanapi.dto.OrderResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderProductMapper {
    List<OrderProduct> getOrder(OrderProductRequestView orderRequestView);

    boolean createOrder(OrderProduct order);

    boolean changeStatusOrder(int statusId, int orderId);

    OrderProduct getOrderProductById(int orderId);
    OrderProduct getOrderProductByOrderDate(LocalDateTime orderDate);
}
