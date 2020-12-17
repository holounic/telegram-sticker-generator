package com.example.demo.controllers;

import com.example.demo.tools.ImageProcessor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {

    @RequestMapping(value="/process", method = RequestMethod.POST, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> postImageToProcess(@RequestParam("file") MultipartFile image) {
        byte[] cropped = ImageProcessor.process(image);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(cropped);
    }

}
