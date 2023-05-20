package com.iclean.icleanapi.config;

import com.iclean.icleanapi.dao.UserMapper;
import com.iclean.icleanapi.domain.User;
import com.iclean.icleanapi.dto.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    @Cacheable(value = "userDetails")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByUserName(username);
        return UserPrinciple.build(user);
    }
}
