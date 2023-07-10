package com.iclean.icleanapi.repository;

import com.iclean.icleanapi.domain.Notification;
import com.iclean.icleanapi.dto.MoneyRequest;
import com.iclean.icleanapi.dto.MoneyResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MoneyMapper {
    boolean updateMoney(MoneyRequest moneyRequest);
    MoneyResponse getMoneyByUserId(int userId);
}
