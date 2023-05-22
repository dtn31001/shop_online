package com.vti.service.Iservice;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {
	String uploadFile(MultipartFile file) throws IOException;

}
