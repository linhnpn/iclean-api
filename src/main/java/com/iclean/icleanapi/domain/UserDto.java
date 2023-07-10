package com.iclean.icleanapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int userId;
    private String username;
    private String fullname;
    private Date dateOfBirth;
    private String gender;
    private String phone;
    private String email;
    private String bio;
    private Integer point;
    private String profilePicture;
    private String roleName;
    private Double longitude;
    private Double latitude;
    private String description;
    private String street;
    private String locationName;
}
