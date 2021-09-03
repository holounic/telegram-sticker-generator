package com.example.demo.service;

import com.example.demo.entity.Sticker;
import com.example.demo.tool.gallery.SearchingMethod;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface ServiceInterface {
    Sticker save(Sticker sticker);
    Optional<Sticker> findById(long id);

    List<Sticker> findByPack(String pack);
    List<Sticker> findByOwner(String owner);
    List<Sticker> findBy(SearchingMethod method, String value);

    List<String> findAllOwners();
    List<String> findAllPacks();

    long findNewestId();
    long findElementsNumber();
    long findPacksNumber();
    long findOwnersNumber();

}
