package com.iclean.icleanapi.service.impl;

import com.iclean.icleanapi.dto.MessageDto;
import com.iclean.icleanapi.dto.ResponseObject;
import com.iclean.icleanapi.service.interf.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public ResponseEntity<ResponseObject> sendMessage(MessageDto mess) {
        return null;
//        try {
//            mess.setMessageId(null);
//            String url = fileStorage.uploadFile(file);
//            job.setThumbnailJobImage(url);
//            job.setDeleted(false);
//            jobRepository.save(job);
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseObject(HttpStatus.ACCEPTED.toString(), "Add new Job Successfully!", null));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Something wrong occur!", null));
//        }
    }

    @Override
    public ResponseEntity<ResponseObject> getMessage(String userId) {
        return null;
    }
    public ResponseEntity<ResponseObject> getMessage(String userId, String employeeId) {
        return null;
    }
}
