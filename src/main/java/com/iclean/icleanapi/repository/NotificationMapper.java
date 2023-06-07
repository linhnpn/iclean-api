package com.iclean.icleanapi.repository;

import com.iclean.icleanapi.domain.Notification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotificationMapper {

    boolean addNotification(Notification notification);

    List<Notification> getNotificationByUserId(int userId);

}
