package com.iclean.icleanapi.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class Inbox {
    private int userId;
    private String fullName;
    private String image;
    private String newestMessage;
    private Date timestamp;
    private boolean isRead;
    private int unreadMess;
}
