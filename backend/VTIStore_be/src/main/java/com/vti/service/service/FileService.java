package com.vti.service.service;

import com.vti.service.Iservice.IFileService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService implements IFileService {

    @Override
    public String uploadFile(MultipartFile file) {
        return StringUtils.cleanPath(file.getOriginalFilename());
    }
}
