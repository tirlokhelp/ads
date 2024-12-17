package com.ks.ads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ks.ads.entity.ImageEntity;
import com.ks.ads.service.ImageService;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    // Endpoint to upload an image
    @PostMapping("/upload")
    public ResponseEntity<ImageEntity> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("fileUrl") String fileUrl) throws IOException {
        ImageEntity savedImage = imageService.saveImage(file,fileUrl);
        return new ResponseEntity<>(savedImage, HttpStatus.CREATED);
    }

    // Endpoint to retrieve an image by its ID
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Optional<ImageEntity> imageEntity = imageService.getImageById(id);
        
        if (imageEntity.isPresent()) {
            return ResponseEntity.ok()
                    .header("Content-Type", imageEntity.get().getFileType())
                    .body(imageEntity.get().getData());
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    @GetMapping("/highest-id")
	public ResponseEntity<byte[]> getImageWithHighestId() {
		Optional<ImageEntity> imageEntity = imageService.getImageWithHighestId();
		if (imageEntity.isPresent()) {
			return ResponseEntity.ok().header("Content-Type", imageEntity.get().getFileType())
					.body(imageEntity.get().getData());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	@GetMapping("/highest")
	public ResponseEntity<ImageEntity> getImageWithHighest() {
	    Optional<ImageEntity> imageEntity = imageService.getImageWithHighestId();
	    System.out.println("Image ID: " + imageEntity.get().getFileName());
        System.out.println("Image URL: " + imageEntity.get().getFileUrl());
	    return imageEntity.map(ResponseEntity::ok)
	                      .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
	}
}
