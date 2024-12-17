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
@RequestMapping("/videos")
public class VideoController {

    private final ImageService imageService; // Reusing ImageService for video handling

    @Autowired
    public VideoController(ImageService imageService) {
        this.imageService = imageService;
    }

    // Endpoint to upload a video
//    @PostMapping("/upload")
//    public ResponseEntity<ImageEntity> uploadVideo(@RequestParam("file") MultipartFile file) throws IOException {
//        ImageEntity savedVideo = imageService.saveImage(file); // Assuming ImageService can handle video as well
//        return new ResponseEntity<>(savedVideo, HttpStatus.CREATED);
//    }

    // Endpoint to retrieve a video by its ID
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getVideo(@PathVariable Long id) {
        Optional<ImageEntity> videoEntity = imageService.getImageById(id); // Assuming your ImageEntity can handle videos too
        
        if (videoEntity.isPresent()) {
            return ResponseEntity.ok()
                    .header("Content-Type", videoEntity.get().getFileType()) // Use proper video MIME type (e.g., video/mp4)
                    .body(videoEntity.get().getData());
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
    
}
