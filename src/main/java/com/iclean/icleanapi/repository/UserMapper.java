package com.iclean.icleanapi.repository;

import com.iclean.icleanapi.domain.User;
import com.iclean.icleanapi.domain.UserDto;
import com.iclean.icleanapi.dto.ChangePasswordForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findUserByUserName(String username);
    UserDto findUserDtoByUserName(String username);
    UserDto findUserDtoByUserNameNoAddressDefault(String username);
    void createUser(User user);
    void changePassword(ChangePasswordForm form);
    List<User> getAllUser();
}
