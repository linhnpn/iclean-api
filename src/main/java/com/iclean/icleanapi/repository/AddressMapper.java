package com.iclean.icleanapi.repository;

import com.iclean.icleanapi.domain.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {
    Address getAddressByUserId(int userId);
    Address getAddressDefaultByUserId(int userId);
    boolean insertUserAddress(Address address);

    boolean deleteUserAddress(int addressId);

    boolean updateUserAddress (Address address);

    Address getAddressById(int addressId);

    List<Address> getAllAddressByUserId(Integer userId);
}
