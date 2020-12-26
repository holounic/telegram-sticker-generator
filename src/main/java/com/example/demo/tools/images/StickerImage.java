package com.example.demo.tools.images;

import org.springframework.web.multipart.MultipartFile;
import java.awt.image.BufferedImage;

public class StickerImage extends AbstractImage<byte[]> {

    private static final int DIM = 512;

    public StickerImage(MultipartFile file) {
        super(file);
    }

    @Override
    protected ImageType getImageType() {
        return ImageType.PNG;
    }

    @Override
    protected int getColourType() {
        return BufferedImage.TYPE_INT_RGB;
    }

    @Override
    protected byte[] convertOutput(BufferedImage image) {
        return toByteArray(image);
    }

    public byte[] squeeze() {
        int originalHeight = image.getHeight();
        int originalWidth = image.getWidth();
        int canvasWidth, canvasHeight;

        if (originalHeight > originalWidth) {
            canvasHeight = DIM;
            canvasWidth = Math.min(DIM, originalWidth);
        } else {
            canvasHeight = Math.min(DIM, originalHeight);
            canvasWidth = DIM;
        }
        return squeeze(canvasWidth, canvasHeight);
    }

    public byte[] preserveDimension() {
        int originalHeight = image.getHeight();
        int originalWidth = image.getWidth();
        int canvasWidth, canvasHeight;
        if (originalHeight > originalWidth) {
            canvasHeight = DIM;
            canvasWidth = DIM * originalWidth / originalHeight;
        } else {
            canvasWidth = DIM;
            canvasHeight = DIM * originalHeight / originalWidth;
        }
        return squeeze(canvasWidth, canvasHeight);
    }

    public byte[] square() {
        return squeeze(DIM, DIM);
    }

}
