package com.example.demo.tool.image;

import com.example.demo.tool.image.dimsetters.Dim;
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
        Dim canvasDim = new Dim(
                image.getWidth(),
                image.getHeight(),
                (x, y) -> Math.min(DIM, Math.min(x, y)),
                (x, y) -> DIM);
        int canvasHeight = canvasDim.height;
        int canvasWidth = canvasDim.width;
        logger.info(String.format("Squeeze, width = %d, height = %d", canvasWidth, canvasHeight));
        return squeeze(canvasWidth, canvasHeight);
    }

    public byte[] preserveDimension() {
        Dim canvasDim = new Dim(
                image.getWidth(),
                image.getHeight(),
                (x, y) -> DIM * Math.min(x, y) / Math.max(x, y),
                (x, y) -> DIM);
        int canvasWidth = canvasDim.width;
        int canvasHeight = canvasDim.height;
        logger.info(String.format("Preserve dimensions, width = %d, height = %d", canvasWidth, canvasHeight));
        return squeeze(canvasWidth, canvasHeight);
    }

    public byte[] square() {
        return squeeze(DIM, DIM);
    }


    public byte[] subImage() {
        Dim canvasDim = new Dim(
                image.getWidth(),
                image.getHeight(),
                (x, y) -> Math.min(x, Math.min(y, DIM)),
                (x, y) -> DIM);
        int canvasWidth = canvasDim.width;
        int canvasHeight = canvasDim.height;
        logger.info(String.format("SubImage, width = %d, height = %d", canvasWidth, canvasHeight));
        return subImage(canvasWidth, canvasHeight);
    }

}
