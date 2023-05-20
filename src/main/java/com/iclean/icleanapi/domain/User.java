package com.iclean.icleanapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    private String username;
    private String password;
    private String fullname;
    private Date dateOfBirth;
    private String gender;
    private String phone;
    private String email;
    private String bio;
    private String profilePicture;
    private String roleName;
}
