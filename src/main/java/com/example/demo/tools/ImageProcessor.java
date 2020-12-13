package com.example.demo.tools;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

public class ImageProcessor {

    private static final int DIM = 512;
    private static final String IMAGE_TYPE = "PNG";

    public static BufferedImage multipartToBuffered(MultipartFile image) {
        try {
            return ImageIO.read(image.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage crop(BufferedImage src) {
        int originalHeight = src.getHeight();
        int originalWidth = src.getWidth();
        int canvasWidth, canvasHeight;

        if (originalHeight > originalWidth) {
            canvasHeight = DIM;
            canvasWidth = Math.min(DIM, originalWidth);
        } else {
            canvasHeight = Math.min(DIM, originalHeight);
            canvasWidth = DIM;
        }

        BufferedImage canvas = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g = canvas.getGraphics();
        g.drawImage(src, 0, 0, canvasWidth, canvasHeight, null);
        g.dispose();
        return canvas;
    }

    public static byte[] toByteArray(BufferedImage image) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, IMAGE_TYPE, os);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return os.toByteArray();
    }

    public static byte[] process(MultipartFile image) {
        Objects.requireNonNull(image);
        BufferedImage bufferedImage = multipartToBuffered(image);
        assert bufferedImage != null;
        return toByteArray(crop(bufferedImage));
    }
}
