package com.example.demo;

import com.example.demo.tools.ImageProcessor;
import com.example.demo.tools.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@RestController
public class ImageController {

    @Autowired
    StorageService service;

    @RequestMapping(value="/process", method = RequestMethod.POST, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> postImageToProcess(@RequestParam("file") MultipartFile image) {
        byte[] cropped = ImageProcessor.process(image);
        File f = new File("suckkk.png");
        try {
            ImageIO.write(ImageProcessor.crop(ImageProcessor.multipartToBuffered(image)), "PNG", f);
        } catch (IOException e) {}

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(cropped);
    }

}
