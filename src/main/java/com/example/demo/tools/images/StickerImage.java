package com.example.demo.tools.images;

import org.springframework.web.multipart.MultipartFile;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

public class StickerImage extends AbstractImage<byte[]> {

    private static final Logger logger = Logger.getLogger(AbstractImage.class.getName());

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
        logger.info(String.format("Squeeze, width = %d, height = %d", canvasWidth, canvasHeight));
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
        logger.info(String.format("Preserve dimensions, width = %d, height = %d", canvasWidth, canvasHeight));
        return squeeze(canvasWidth, canvasHeight);
    }

    public byte[] square() {
        return squeeze(DIM, DIM);
    }

    public byte[] subImage() {
        int originalHeight = image.getHeight();
        int originalWidth = image.getWidth();
        int canvasHeight, canvasWidth;
        logger.info(String.format("Original size: w = %d, h = %d", originalWidth, originalHeight));
        if (originalHeight > originalWidth) {
            canvasHeight = DIM;
            canvasWidth = Math.min(originalWidth, DIM);
        } else {
            canvasWidth = DIM;
            canvasHeight = Math.min(originalHeight, DIM);
        }
        logger.info(String.format("SubImage, width = %d, height = %d", canvasWidth, canvasHeight));
        return subImage(canvasWidth, canvasHeight);
    }

}
