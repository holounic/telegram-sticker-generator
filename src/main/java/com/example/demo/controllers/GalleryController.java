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

@RestController
public class GalleryController {

    @Autowired
    StickerService service;

    @RequestMapping(value = "/gallery/method={method}/value={value}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> findSticker(@PathVariable SearchingMethod method, @PathVariable String value) {
        System.out.println(method);
        List<Sticker> result = service.findBy(method, value);
        byte[] sticker = GalleryResponseBuilder.buildMonoResponse(result);
        // TODO: handle empty results
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(sticker);
    }
}
