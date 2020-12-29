package com.example.demo.tools.images;

import org.springframework.web.multipart.MultipartFile;

public class StickerUtils {
    public static byte[] process(MultipartFile file, ProcessingMethod method) {
        StickerImage image = new StickerImage(file);
        return switch (method) {
            case SQUARE -> image.square();
            case SQUEEZE -> image.squeeze();
            case PRESERVE_DIM -> image.preserveDimension();
            case SUBIMAGE -> image.subImage();
        };
    }
}
