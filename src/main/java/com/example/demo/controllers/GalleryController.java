package com.example.demo.controllers;

import com.example.demo.entities.Sticker;
import com.example.demo.services.StickerService;
import com.example.demo.tools.gallery.GalleryResponseBuilder;
import com.example.demo.tools.gallery.SearchingMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class GalleryController {

    private static final Logger logger = Logger.getLogger(GalleryController.class.getName());

    @Autowired
    StickerService service;

    @RequestMapping(value = "/gallery/method={method}/value={value}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> findSticker(@PathVariable SearchingMethod method, @PathVariable String value) {
        logger.info(String.format("Method: %s, Value: %s", method, value));
        List<Sticker> result = service.findBy(method, value);
        byte[] sticker = GalleryResponseBuilder.buildMonoResponse(result);
        logger.info("Found result: " + (sticker != null));
        // TODO: handle empty results
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(sticker);
    }
}
