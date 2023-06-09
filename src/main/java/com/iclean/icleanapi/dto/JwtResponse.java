package com.iclean.icleanapi.dto;

import com.iclean.icleanapi.domain.User;
import com.iclean.icleanapi.domain.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String accessToken;
    private UserDto user;
}
