package com.example.demo.services;

import com.example.demo.entities.Sticker;
import com.example.demo.tools.gallery.SearchingMethod;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface StickerService {
    Sticker save(Sticker sticker);
    Optional<Sticker> findById(long id);

    List<Sticker> findByPack(String pack);
    List<Sticker> findByOwner(String owner);
    List<Sticker> findBy(SearchingMethod method, String value);

    List<String> findAllOwners();
    List<String> findAllPacks();

}
