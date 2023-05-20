package com.iclean.icleanapi.dao;

import com.iclean.icleanapi.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findUserByUserName(String username);
}
