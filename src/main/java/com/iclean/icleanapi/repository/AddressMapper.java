package com.iclean.icleanapi.repository;

import com.iclean.icleanapi.domain.Address;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressMapper {
    Address getAddressByUserId(int userId);

    boolean insertUserAddress(Address address);

    boolean deleteUserAddress(int userId);

    boolean updateUserAddress (Address address);
}