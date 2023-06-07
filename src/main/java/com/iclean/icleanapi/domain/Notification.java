package com.iclean.icleanapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private int notificationId;

    private String detail;

    private LocalDateTime timestamp;

    private int isRead;

    private int isDelete;

    private int userId;
}
