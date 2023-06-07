package com.iclean.icleanapi.service.impl;

import com.iclean.icleanapi.constant.StatusOrder;
import com.iclean.icleanapi.domain.Address;
import com.iclean.icleanapi.domain.Notification;
import com.iclean.icleanapi.domain.Order;
import com.iclean.icleanapi.dto.*;
import com.iclean.icleanapi.repository.AddressMapper;
import com.iclean.icleanapi.repository.JobMapper;
import com.iclean.icleanapi.repository.NotificationMapper;
import com.iclean.icleanapi.repository.OrderMapper;
import com.iclean.icleanapi.service.interf.OrderService;
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
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private JobMapper jobMapper;

    @Override
    public ResponseEntity<ResponseObject> getFeedback() {

        try {
            List<Order> feedback = orderMapper.getFeedback();
            if (feedback == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Job list is empty.", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.toString(), "Feedback list!", feedback));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> changeStatusOrder(int orderId, int statusId) {
        try {
            //Get LocalDateTime.now() theo format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String formatDateTime = now.format(formatter);

            Order existOrder = orderMapper.getOrderById(orderId);
            existOrder.setStatusId(statusId);

            if (existOrder == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Change status fail!", null));
            } else {
                //get info Job Order
                EmployeeJobResponse job = jobMapper.getJobById(existOrder.getJobId(), existOrder.getEmployeeId());

                Notification notificationForUser = new Notification();
                notificationForUser.setUserId(existOrder.getRenterId());

                Notification notificationForemployee = new Notification();
                notificationForemployee.setUserId(existOrder.getEmployeeId());

                if (existOrder.getStatusId().equals(StatusOrder.IN_PROCESS.getValue())) {
                    //set workStart = now
                    existOrder.setWorkStart(LocalDateTime.parse(formatDateTime, formatter));
                    notificationForUser.setTimestamp(existOrder.getWorkStart());
                    notificationForemployee.setTimestamp(existOrder.getWorkStart());

                    notificationForUser.setDetail("Your booking " + job.getJobName() + " from staff " + job.getEmployeeName() + " is in process");
                    notificationForemployee.setDetail(job.getJobName() + " service, is in process!");

                } else if (existOrder.getStatusId().equals(StatusOrder.DONE.getValue())) {
                    if (!existOrder.getWorkStart().plusHours(existOrder.getWorkTime()).isBefore(LocalDateTime.now())) {
                        return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseObject(HttpStatus.FAILED_DEPENDENCY.toString(), "Cannot done before workDate!", null));
                    }
                    //set workEnd = now
                    existOrder.setWorkEnd(LocalDateTime.parse(formatDateTime, formatter));
                    notificationForUser.setTimestamp(existOrder.getWorkEnd());
                    notificationForemployee.setTimestamp(existOrder.getWorkEnd());

                    notificationForUser.setDetail("Your booking " + job.getJobName() + " from staff " + job.getEmployeeName() + " is done");
                    notificationForemployee.setDetail(job.getJobName() + " service, is done!");
                } else if (existOrder.getStatusId().equals(StatusOrder.UPCOMING.getValue())) {
                    //set workEnd = now
                    existOrder.setWorkStart(LocalDateTime.parse(formatDateTime, formatter));
                    notificationForUser.setTimestamp(existOrder.getWorkStart());
                    notificationForemployee.setTimestamp(existOrder.getWorkStart());

                    notificationForUser.setDetail("UP COMING service " + job.getJobName() + " from staff " + job.getEmployeeName());
                    notificationForemployee.setDetail("UP COMING service " + job.getJobName() + "!");
                } else if (existOrder.getStatusId().equals(StatusOrder.CANCEL.getValue())) {
                    //set workEnd = now
                    existOrder.setWorkEnd(LocalDateTime.parse(formatDateTime, formatter));
                    notificationForUser.setTimestamp(existOrder.getWorkEnd());
                    notificationForemployee.setTimestamp(existOrder.getWorkEnd());

                    notificationForUser.setDetail("Your booking " + job.getJobName() + " from staff " + job.getEmployeeName() + " is cancel");
                    notificationForemployee.setDetail(job.getJobName() + " service, is cancel!");
                }
                //ADD NOTI FOR USER AND STAFF
                boolean checkNotiforUser = notificationMapper.addNotification(notificationForUser);
                if (checkNotiforUser) {
                    boolean checkNotiForEmployee = notificationMapper.addNotification(notificationForemployee);
                    if (!checkNotiForEmployee)
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Change status Failed!", null));
                }

                //UPDATE STATUS FOR ORDER
                boolean checkChange = orderMapper.changeStatusOrder(statusId, existOrder);
                if (checkChange) {
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseObject(HttpStatus.OK.toString(), "Change status success!", existOrder));
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Change status fail!", null));
            }
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ResponseObject> createOrder(NewOrderForm form) {
        try {
            Address address = addressMapper.getAddressDefaultByUserId(form.getRenterId());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String formatDateTime = now.format(formatter);
            form.setOrderDate(LocalDateTime.parse(formatDateTime, formatter));
            Order order = new Order();

            order.setLocation(address.getDescription());
            order.setLongitude(address.getLongitude());
            order.setLatitude(address.getLatitude());
            order.setWorkDate(form.getWorkDate());
            order.setWorkTime(form.getWorkTime());
            order.setOrderDate(LocalDateTime.parse(formatDateTime, formatter));
            order.setRenterId(form.getRenterId());
            order.setEmployeeId(form.getEmployeeId());
            order.setJobId(form.getJobId());
            order.setStatusId(StatusOrder.UNDONE.getValue());
            order.setVoucherCode(form.getVoucherCode());

            //get info Job Order
            EmployeeJobResponse job = jobMapper.getJobById(order.getJobId(), order.getEmployeeId());

            Notification notificationForUser = new Notification();
            notificationForUser.setTimestamp(order.getOrderDate());
            notificationForUser.setUserId(order.getRenterId());

            Notification notificationForemployee = new Notification();
            notificationForemployee.setTimestamp(order.getOrderDate());
            notificationForemployee.setUserId(order.getEmployeeId());

            boolean check = orderMapper.createOrder(order);
            if (check) {
                //ADD NOTI FOR USER AND STAFF
                notificationForUser.setDetail("You have successfully booked " + job.getJobName() + " from staff " + job.getEmployeeName() + " , please wait for our staff to confirm!");
                notificationForemployee.setDetail("You have just got a new booking for " + job.getJobName() + " service, please confirm with the customer!");
                boolean checkNotiforUser = notificationMapper.addNotification(notificationForUser);
                if (checkNotiforUser) {
                    boolean checkNotiForEmployee = notificationMapper.addNotification(notificationForemployee);
                    if (checkNotiForEmployee) {
                        return ResponseEntity.status(HttpStatus.OK)
                                .body(new ResponseObject(HttpStatus.OK.toString(), "Create Order success!", null));
                    }
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Create Order Failed!", null));
                }

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Create Order Failed!", null));
            } else {
                //ADD NOTI FOR USER AND STAFF IF FAILD
                notificationForUser.setDetail("You have failed to book " + job.getJobName() + " from staff " + job.getEmployeeName() + " , please book again!");

                boolean checkNotiforUser = notificationMapper.addNotification(notificationForUser);
                if (checkNotiforUser) {
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseObject(HttpStatus.OK.toString(), "Create Order success!", null));
                }

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Create Order Failed!", null));

            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ResponseObject> getOrder(Integer userId, Integer employeeId, Integer status) {
        try {
            OrderRequestView orderRequestView = new OrderRequestView(userId, employeeId, status);
            List<Order> feedback = orderMapper.getOrder(orderRequestView);
            if (feedback == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Job list is empty.", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(HttpStatus.OK.toString(), "Feedback list!", feedback));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), null));
        }
    }


}
