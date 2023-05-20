package com.iclean.icleanapi.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginForm {
    private String username;

    private String password;

}
