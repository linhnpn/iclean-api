package com.iclean.icleanapi.repository;

import com.iclean.icleanapi.domain.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    Role findRoleName(String roleId);
    Role findRoleByRoleName(String name);
}
