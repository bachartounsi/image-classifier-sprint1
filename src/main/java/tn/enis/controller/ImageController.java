package tn.enis.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.enis.dto.ClassificationResponse;
import tn.enis.service.ImageClassificationService;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    private final ImageClassificationService classificationService;

    public ImageController(ImageClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @PostMapping("/classify")
    public ResponseEntity<ClassificationResponse> classifyImage(@RequestParam("file") MultipartFile file) {
        ClassificationResponse response = classificationService.classify(file);
        return ResponseEntity.ok(response);
    }
}

