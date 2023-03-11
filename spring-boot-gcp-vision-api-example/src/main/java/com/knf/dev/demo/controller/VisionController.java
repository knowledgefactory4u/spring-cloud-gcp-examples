package com.knf.dev.demo.controller;


import com.knf.dev.demo.service.VisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class VisionController {

    @Autowired
    private VisionService visionService;

    //Extract the text in an image
    @PostMapping("/extractTextFromImage")
    public String extractTextFromImage(
            @RequestParam MultipartFile file) {

        return visionService.extractTextFromImage(file);

    }

    //Get landmark from Image
    @PostMapping("/getLandmarkFromImage")
    public String getLandmarkFromImage(
            @RequestParam MultipartFile file) {

        return visionService.getLandmarkFromImage(file);

    }

    //Detect label from image
    @PostMapping("/detectLabelFromImage")
    public String detectLabelFromImage(
            @RequestParam MultipartFile file) {

        return visionService.detectLabelFromImage(file);
    }

    //Extract the text in a pdf
    @PostMapping("/extractTextFromPdf")
    public List<String> extractTextFromPdf(
            @RequestParam MultipartFile file) {

        return visionService.extractTextFromPdf(file);
    }

    //Detect face from Image
    @PostMapping("/detectFaceFromImage")
    public ResponseEntity<byte[]> detectFaceFromImage(
            @RequestParam MultipartFile file) throws IOException {

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).
                body(visionService.detectFaceFromImage(file));
    }
}