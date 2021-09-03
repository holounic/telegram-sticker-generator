package com.example.demo.controller;

import com.example.demo.entity.Sticker;
import com.example.demo.service.ServiceInterface;
import com.example.demo.tool.images.ProcessingMethod;
import com.example.demo.tool.images.StickerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.logging.Logger;

@RestController
public class ImageController {

    private static final Logger logger = Logger.getLogger(ImageController.class.getName());

    @Autowired
    ServiceInterface service;

    @RequestMapping(value="/process", method = RequestMethod.POST)
    public ResponseEntity<byte[]> postImageToProcess(@RequestParam("file") MultipartFile file,
                                                     @RequestParam("owner") String owner,
                                                     @RequestParam("pack") String pack,
                                                     @RequestParam("method") ProcessingMethod method) {
        logger.info(String.format("Using %s method to process image", method));
        byte[] cropped = StickerUtils.process(file, method);
        service.save(new Sticker(owner, pack, cropped));

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(cropped);
    }

}
