package com.iclean.icleanapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private int messageId;
    private Date sentDatetime;
    private String content;
    private String file_link;
    private int from_id;
    private int to_id;
    private int status;
}
