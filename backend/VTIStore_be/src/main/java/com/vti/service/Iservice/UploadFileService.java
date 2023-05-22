package com.vti.service.Iservice;

import com.vti.entity.ProductImage;
import com.vti.repository.Irepository.ImageRepo;
import com.vti.ultils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Service
public class UploadFileService {
    @Autowired
    ImageRepo imageRepo;

    public ResponseEntity<?> uploadImage(MultipartFile file) throws IOException {
        ProductImage image = new ProductImage();
     //   image.setName(Arrays.stream(Objects.requireNonNull(file.getOriginalFilename()).split("\\.")).findFirst().orElse(""));
        image.setType(file.getContentType());
        image.setImageData(ImageUtil.compressImage(file.getBytes()));
        imageRepo.save(image);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public void uploadImageFromProduct(MultipartFile file) throws IOException {
        ProductImage image = new ProductImage();
     //   image.setName(Arrays.stream(Objects.requireNonNull(file.getOriginalFilename()).split("\\.")).findFirst().orElse(""));
        image.setType(file.getContentType());
        image.setImageData(ImageUtil.compressImage(file.getBytes()));
        imageRepo.save(image);
    }

    public ResponseEntity<?> getImage(String name){
        name = Arrays.stream(name.split("\\.")).findFirst().orElse("");
        Optional<ProductImage> productImage = imageRepo.findByName(name);
        byte[] imageByte = new byte[0];
        if (productImage.isPresent()) {
            imageByte = ImageUtil.decompressImage(productImage.get().getImageData());
        }
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/jpeg")).body(imageByte);
    }
}
