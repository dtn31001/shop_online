package com.vti.service.service;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.vti.service.Iservice.IFileService;

@Service
public class AWSS3Service implements IFileService {

//	private AmazonS3Client awS3Client;

	@Autowired
	private Cloudinary cloudinary;


	@Override
	public String uploadFile(MultipartFile file) throws IOException {
		return cloudinary.uploader()
				.upload(file.getBytes(),
						Map.of("public_id", UUID.randomUUID().toString()))
				.get("url")
				.toString();

	}

//		String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
//
//		String key = UUID.randomUUID().toString() + "." + filenameExtension;
//
//		ObjectMetadata metadata = new ObjectMetadata();
//
//		metadata.setContentLength(file.getSize());
//		metadata.setContentType(file.getContentType());
//		try {
//			awS3Client.putObject("vuongvuong", key, file.getInputStream(), metadata);
//		} catch (IOException e) {
//			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
//					"Error occured while uploading the file");
//		}
//		awS3Client.setObjectAcl("vuongvuong", key, CannedAccessControlList.PublicRead);
//		return awS3Client.getResourceUrl("vuongvuong", key);
	}
// upload, download, delete, getList, private, 
	
	// kết nối với redis lưu text token
	// lưu log (dùng mongodb, file(log4j) loại noSQL
	// docker khái niệm cách sử dụng: vd: kho chứa ứng  ứng dụng CICD
	
//	jsp,servelet
	
//}
