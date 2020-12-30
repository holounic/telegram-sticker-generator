package com.example.demo.tools.gallery;

import com.example.demo.entities.Sticker;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class GalleryResponseBuilder {

    private static final Random random = new Random(12345);

    public static List<Long> buildMultiResponse(List<Sticker> stickers) {
        return Objects.requireNonNull(stickers).stream()
                .map(Sticker::getId)
                .collect(Collectors.toList());
    }

}
