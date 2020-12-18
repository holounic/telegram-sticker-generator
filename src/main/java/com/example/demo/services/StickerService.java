package com.example.demo.services;

import com.example.demo.entities.Sticker;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface StickerService {
    Sticker save(Sticker sticker);

    List<Sticker> findByPack(String pack);
    List<Sticker> findByOwner(String owner);
}
