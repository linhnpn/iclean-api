package com.iclean.icleanapi.repository;

import com.iclean.icleanapi.domain.Message;
import com.iclean.icleanapi.dto.DetailInbox;
import com.iclean.icleanapi.dto.MessageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    void sendMessage(MessageDto dto);
    List<Message> getInbox(DetailInbox detailInbox);
    List<MessageDto> getAllInbox(String userId);
}
