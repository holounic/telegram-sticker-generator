package com.example.demo.controllers;

import com.example.demo.tools.ProcessingMethod;
import com.example.demo.tools.StickerUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {

    @RequestMapping(value="/process", method = RequestMethod.POST)
    public ResponseEntity<byte[]> postImageToProcess(@RequestParam("file") MultipartFile file,
                                                     @RequestParam("owner") String owner,
                                                     @RequestParam("pack") String pack,
                                                     @RequestParam("method") ProcessingMethod method) {
        byte[] cropped = StickerUtils.process(file, method);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(cropped);
    }

}
