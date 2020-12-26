package com.example.demo.services;

import com.example.demo.entities.Sticker;
import com.example.demo.tools.gallery.SearchingMethod;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface StickerService {
    Sticker save(Sticker sticker);

    List<Sticker> findByPack(String pack);
    List<Sticker> findByOwner(String owner);
    List<Sticker> findBy(SearchingMethod method, String value);
}
