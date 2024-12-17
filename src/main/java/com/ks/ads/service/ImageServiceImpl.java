package com.ks.ads.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ks.ads.entity.ImageEntity;
import com.ks.ads.repository.ImageRepository;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public ImageEntity saveImage(MultipartFile file, String fileUrl) throws IOException {
        ImageEntity imageEntity = ImageEntity.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .fileUrl(fileUrl)
                .data(file.getBytes()) // Store binary data
                .build();
        return imageRepository.save(imageEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ImageEntity> getImageById(Long id) {
        return imageRepository.findById(id);
    }
    
    @Override 
    @Transactional(readOnly = true)
    public Optional<ImageEntity> getImageWithHighestId() 
    { 
    	return imageRepository.findTopByOrderByIdDesc();
    }
}
