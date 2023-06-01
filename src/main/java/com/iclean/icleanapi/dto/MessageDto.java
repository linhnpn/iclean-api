package com.iclean.icleanapi.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class MessageDto {
    private Integer messageId;
    private Integer fromId;
    private Integer toId;
    private String content;
    private Date sentDatetime;
    private String fileLink;
    private boolean status;
}
