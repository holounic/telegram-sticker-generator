package com.example.demo.tools.gallery;

import com.example.demo.entities.Sticker;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GalleryResponseBuilder {

    private static final Random random = new Random(12345);

    public static byte[][] buildMultiResponse(List<Sticker> stickers) {
        Objects.requireNonNull(stickers);
        byte[][] result = new byte[stickers.size()][];
        for (int i = 0; i < result.length; i++) {
            byte[] image = stickers.get(i).getImage();
            result[i] = Arrays.copyOf(image, image.length);
        }
        return result;
    }

    public static byte[] buildMonoResponse(List<Sticker> stickers) {
        Objects.requireNonNull(stickers);
        if (stickers.isEmpty()) return null;
        return stickers.get(random.nextInt(stickers.size())).getImage();
    }
}
