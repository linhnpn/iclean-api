package com.iclean.icleanapi.repository;

import com.iclean.icleanapi.domain.Voucher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VoucherMapper {
    Voucher getVoucherByCode(String code);
}
