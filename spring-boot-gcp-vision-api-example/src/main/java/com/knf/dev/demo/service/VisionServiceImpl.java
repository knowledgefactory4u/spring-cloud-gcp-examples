package com.knf.dev.demo.service;

import com.google.cloud.spring.vision.CloudVisionTemplate;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.FaceAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Vertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Service
public class VisionServiceImpl implements VisionService{

    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public String extractTextFromImage(MultipartFile file) {

        String textFromImage = cloudVisionTemplate.
                extractTextFromImage(file.getResource());

        return textFromImage;
    }

    @Override
    public String getLandmarkFromImage(MultipartFile file) {

        AnnotateImageResponse response =
                cloudVisionTemplate.analyzeImage(file.getResource(),
                        Feature.Type.LANDMARK_DETECTION);

        return response.getLandmarkAnnotationsList().toString();
    }

    @Override
    public String detectLabelFromImage(MultipartFile file) {

        AnnotateImageResponse response =
                cloudVisionTemplate.analyzeImage(
                        file.getResource(), Feature.Type.LABEL_DETECTION);

        return response.getLabelAnnotationsList().toString();
    }

    @Override
    public List<String> extractTextFromPdf(MultipartFile file) {

        List<String> texts =
                cloudVisionTemplate.extractTextFromPdf(file.getResource());

        return texts;
    }

    @Override
    public byte[] detectFaceFromImage(MultipartFile file)
            throws IOException {

        AnnotateImageResponse response = cloudVisionTemplate.analyzeImage(
                file.getResource(), Feature.Type.FACE_DETECTION);
        Resource outputImageResource = resourceLoader.
                getResource("file:src/main/resources/output.jpg");

        byte [] image = writeWithFaces(file,
                outputImageResource.getFile().toPath(),
                   response.getFaceAnnotationsList());

        return image;
    }

    private static byte[] writeWithFaces(MultipartFile file,
                  Path outputPath, List<FaceAnnotation> faces)
                        throws IOException {

        BufferedImage img = ImageIO.read(file.getInputStream());
        annotateWithFaces(img, faces);

        //Write file to resource folder, check resources folder
        ImageIO.write(img, "jpg", outputPath.toFile());

        //And
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", baos);
        //BufferedImage to byte array
        byte[] image = baos.toByteArray();

        return image;
    }


    public static void annotateWithFaces(BufferedImage img,
                          List<FaceAnnotation> faces) {

        for (FaceAnnotation face : faces) {
            annotateWithFace(img, face);
        }
    }


    private static void annotateWithFace(BufferedImage img,
                                FaceAnnotation face) {

        Graphics2D gfx = img.createGraphics();
        Polygon poly = new Polygon();
        for (Vertex vertex : face.getFdBoundingPoly().getVerticesList()) {
            poly.addPoint(vertex.getX(), vertex.getY());
        }
        gfx.setStroke(new BasicStroke(5));
        gfx.setColor(new Color(0xFFFF00));
        gfx.draw(poly);
    }
}
