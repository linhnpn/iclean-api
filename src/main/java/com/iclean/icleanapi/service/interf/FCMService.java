package com.iclean.icleanapi.service.interf;

import com.iclean.icleanapi.dto.NotificationRequestDto;
import com.iclean.icleanapi.dto.SubscriptionRequestDto;

public interface FCMService {
    public void subscribeToTopic(SubscriptionRequestDto subscriptionRequestDto);
    public void unsubscribeFromTopic(SubscriptionRequestDto subscriptionRequestDto);
    public String sendPnsToTopic(NotificationRequestDto notificationRequestDto);
}
