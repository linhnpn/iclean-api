package com.iclean.icleanapi.service.impl;

import com.iclean.icleanapi.constant.StatusOrder;
import com.iclean.icleanapi.constant.StatusProductOrder;
import com.iclean.icleanapi.domain.*;
import com.iclean.icleanapi.dto.*;
import com.iclean.icleanapi.repository.*;
import com.iclean.icleanapi.service.interf.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private OrderProductMapper orderProductMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private NotificationMapper notificationMapper;
    @Override
    public ResponseEntity<ResponseObject> changeStatusOrder(int orderId, int statusId) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String formatDateTime = now.format(formatter);

            OrderProduct existOrder = orderProductMapper.getOrderProductById(orderId);
            existOrder.setOrderStatus(statusId);

            Notification notification = new Notification();
            notification.setUserId(existOrder.getUserId());
            notification.setTimestamp(LocalDateTime.parse(formatDateTime, formatter));

            if (existOrder.getOrderStatus().equals(StatusProductOrder.CANCEL.getValue())) {
                notification.setDetail("Your order have been cancel");

            } else if (existOrder.getOrderStatus().equals(StatusProductOrder.WAITING_TO_SHIP.getValue())) {
                notification.setDetail("Add Order Successful, please waiting for ship!");

            } else if (existOrder.getOrderStatus().equals(StatusProductOrder.WAITING_TO_SEND.getValue())) {
                notification.setDetail("Your order has arrived, please receive it!");
            } else if (existOrder.getOrderStatus().equals(StatusOrder.DONE.getValue())) {
                notification.setDetail("Your order has been successfully created and paid for!");
            }
            boolean checkAdd = notificationMapper.addNotification(notification);
            if (!checkAdd) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Change status Failed!", null));
            }

            boolean checkChange = orderProductMapper.changeStatusOrder(statusId, existOrder.getOrderId());
            if (checkChange) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject(HttpStatus.OK.toString(), "Change status success!", existOrder));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Change status fail!", null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ResponseObject> createOrderProduct(NewOrderProductForm form) {
        try {
            Address address = addressMapper.getAddressDefaultByUserId(form.getUserId());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String formatDateTime = now.format(formatter);
            OrderProduct order = new OrderProduct();
            order.setOrderDate(LocalDateTime.parse(formatDateTime, formatter));
            order.setUserId(form.getUserId());
            order.setOrderStatus(StatusProductOrder.WAITING_TO_SHIP.getValue());
            order.setAddress(address.getDescription());
            double sum = 0;
            for (NewOrderProduct item :
                    form.getOrderProducts()) {
                Product product = productMapper.getProductByID(item.getProductId());
                if (product != null) {
                    sum += product.getPrice() * item.getQuantity();
                    item.setUnitPrice(product.getPrice());
                }
            }
            order.setTotalAmount(sum);
            boolean check = orderProductMapper.createOrder(order);
            OrderProduct orderCheck = orderProductMapper.getOrderProductByOrderDate(order.getOrderDate());
            for (NewOrderProduct item :
                    form.getOrderProducts()) {
                    item.setOrderId(orderCheck.getOrderId());
                    orderItemMapper.createOrderItem(item);
                }

            Notification notificationForUser = new Notification();
            notificationForUser.setTimestamp(order.getOrderDate());
            notificationForUser.setUserId(order.getUserId());

            if (check) {
                notificationForUser.setDetail("You have successfully bought the product!");
                boolean notification = notificationMapper.addNotification(notificationForUser);
                if (notification) {
                        return ResponseEntity.status(HttpStatus.OK)
                                .body(new ResponseObject(HttpStatus.OK.toString(), "Create Order success!", orderCheck));
                }
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Create Order Failed!", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> getOrderProduct(Integer userId, String category, Integer status) {
        try {
            OrderProductRequestView orderRequestView = new OrderProductRequestView(userId, category, status);
            List<OrderProduct> orders = orderProductMapper.getOrder(orderRequestView);
            if (orders == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Job list is empty.", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.toString(), "Feedback list!", orders));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }
}
