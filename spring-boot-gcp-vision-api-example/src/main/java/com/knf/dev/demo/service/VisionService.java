package com.knf.dev.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VisionService {

    String extractTextFromImage(MultipartFile file);

    String getLandmarkFromImage(MultipartFile file);

    String detectLabelFromImage(MultipartFile file);

    List<String> extractTextFromPdf(MultipartFile file);

    byte [] detectFaceFromImage(MultipartFile file) throws IOException;

}
