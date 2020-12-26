package com.example.demo.controllers;

import com.example.demo.entities.Sticker;
import com.example.demo.services.StickerService;
import com.example.demo.tools.images.ProcessingMethod;
import com.example.demo.tools.images.StickerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {

    @Autowired
    StickerService service;

    @RequestMapping(value="/process", method = RequestMethod.POST)
    public ResponseEntity<byte[]> postImageToProcess(@RequestParam("file") MultipartFile file,
                                                     @RequestParam("owner") String owner,
                                                     @RequestParam("pack") String pack,
                                                     @RequestParam("method") ProcessingMethod method) {
        byte[] cropped = StickerUtils.process(file, method);
        service.save(new Sticker(owner, pack, cropped));

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(cropped);
    }

}
