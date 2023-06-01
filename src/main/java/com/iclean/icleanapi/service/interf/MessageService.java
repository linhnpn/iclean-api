package com.iclean.icleanapi.service.interf;

import com.iclean.icleanapi.dto.MessageDto;
import com.iclean.icleanapi.dto.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface MessageService {
    public ResponseEntity<ResponseObject> sendMessage(MessageDto mess);
    public ResponseEntity<ResponseObject> getMessage(String userId);
}
