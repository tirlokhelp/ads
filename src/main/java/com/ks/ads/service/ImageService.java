package com.ks.ads.service;

import org.springframework.web.multipart.MultipartFile;

import com.ks.ads.entity.ImageEntity;

import java.io.IOException;
import java.util.Optional;

public interface ImageService {
    ImageEntity saveImage(MultipartFile file,String fileUrl) throws IOException;
    Optional<ImageEntity> getImageById(Long id);
	Optional<ImageEntity> getImageWithHighestId();
}