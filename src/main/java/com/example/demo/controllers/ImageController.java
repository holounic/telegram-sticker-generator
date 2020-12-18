package com.example.demo.controllers;

import com.example.demo.tools.StickerImage;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {

    @RequestMapping(value="/process", method = RequestMethod.POST)
    public ResponseEntity<byte[]> postImageToProcess(@RequestParam("file") MultipartFile file,
                                                     @RequestParam("owner") String owner,
                                                     @RequestParam("pack") String pack) {
        byte[] cropped = new StickerImage(file).preserveDimension();
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(cropped);
    }

}
