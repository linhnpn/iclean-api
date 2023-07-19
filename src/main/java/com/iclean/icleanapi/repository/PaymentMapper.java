package com.iclean.icleanapi.repository;

import com.iclean.icleanapi.domain.PaymentHistory;
import com.iclean.icleanapi.dto.PaymentHistoryResponse;
import com.iclean.icleanapi.dto.StatisticResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PaymentMapper {
    boolean AddPayment(PaymentHistory request);
    List<PaymentHistoryResponse> GetAllPayment();

    List<StatisticResponse> GetStatistic();
    List<PaymentHistoryResponse> GetPaymentByUserID(int userId);

}
