package com.example.demo.tools.images;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public abstract class AbstractImage<T> {
    protected BufferedImage image;

    public AbstractImage(BufferedImage image) {
        this.image = image;
    }

    public AbstractImage(MultipartFile file) {
        this.image = multipartToBuffered(file);
    }

    protected abstract ImageType getImageType();
    protected abstract int getColourType();
    protected abstract T convertOutput(BufferedImage image);

    private BufferedImage multipartToBuffered(MultipartFile image) {
        try {
            return ImageIO.read(image.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    protected byte[] toByteArray(BufferedImage image) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, getImageType().toString(), os);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return os.toByteArray();
    }

    protected byte[] toByteArray() {
        return toByteArray(image);
    }

    public T squeeze(int width, int height) {
        BufferedImage canvas = new BufferedImage(width, height, getColourType());
        Graphics2D g = canvas.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return convertOutput(canvas);
    }

    public T subImage(int width, int height) {
        return convertOutput(image.getSubimage(0, 0, width, height));
    }

    public BufferedImage getImage() {
        return image;
    }
}
