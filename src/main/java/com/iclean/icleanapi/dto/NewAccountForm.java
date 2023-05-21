package com.iclean.icleanapi.dto;

import lombok.Data;

@Data
public class NewAccountForm {
    private String username;
    private String password;
    private String fullname;
    private String dateOfBirth;
    private String gender;
    private String phone;
    private String email;
}
