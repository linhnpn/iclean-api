package com.iclean.icleanapi.service.interf;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorage {
    public String uploadFile(MultipartFile file);
    public boolean deleteFile(String url);
}
