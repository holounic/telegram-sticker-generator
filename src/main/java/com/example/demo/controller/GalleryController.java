package com.example.demo.controller;

import com.example.demo.entity.Sticker;
import com.example.demo.service.ServiceInterface;
import com.example.demo.tool.gallery.GalleryResponseBuilder;
import com.example.demo.tool.gallery.SearchingMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;

@RestController
@RequestMapping("/gallery")
public class GalleryController {

    private static final Logger logger = Logger.getLogger(GalleryController.class.getName());

    @Autowired
    ServiceInterface service;

    @RequestMapping(value = "/method={method}/value={value}", method = RequestMethod.GET)
    public ResponseEntity<List<Long>> getIds(@PathVariable SearchingMethod method, @PathVariable String value) {

        List<Sticker> result = service.findBy(method, value);
        List<Long> ids = GalleryResponseBuilder.buildMultiResponse(result);
        logger.info(String.format("Found %d stickers, Method: %s, Value: %s", ids.size(), method, value));
        // TODO: handle empty results
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ids);
    }

    @RequestMapping(value = "/sticker/id={id}/image", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getStickerImage(@PathVariable long id) {
        Optional<Sticker> sticker = service.findById(id);
        logger.info(String.format("Found %s sticker with id %d", sticker.isPresent() ? "" : "no", id));
        if (sticker.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(sticker.get().getImage());
    }

    @RequestMapping(value = "/sticker/id={id}/data", method = RequestMethod.GET)
    public ResponseEntity<Sticker> getStickerData(@PathVariable long id) {
        Optional<Sticker> sticker = service.findById(id);
        if (sticker.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(sticker.get());
    }

    @RequestMapping(value = "/packs", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getPacks() {
        List<String> packs = service.findAllPacks();
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(packs);
    }

    @RequestMapping(value = "/owners", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getOwners() {
        List<String> owners = service.findAllOwners();
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(owners);
    }

    @RequestMapping(value = "/packs/number", method = RequestMethod.GET)
    public ResponseEntity<Long> getPacksNumber() {
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findPacksNumber());
    }

    @RequestMapping(value = "/stickers/number", method = RequestMethod.GET)
    public ResponseEntity<Long> getStickersNumber() {
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findElementsNumber());
    }

    @RequestMapping(value = "/owners/number", method = RequestMethod.GET)
    public ResponseEntity<Long> getOwnersNumber() {
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findOwnersNumber());
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public ResponseEntity<Long> getRandomId() {
        long newestId = service.findNewestId();
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(newestId - new Random().nextInt((int) (newestId - 1)));
    }

}
