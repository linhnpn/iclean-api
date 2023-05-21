package com.iclean.icleanapi.repository;

import com.iclean.icleanapi.domain.User;
import com.iclean.icleanapi.dto.ChangePasswordForm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findUserByUserName(String username);
    void createUser(User user);
    void changePassword(ChangePasswordForm form);
}
