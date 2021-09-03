package com.example.demo.service;

import com.example.demo.entity.Sticker;
import com.example.demo.repository.StickerRepository;
import com.example.demo.tool.gallery.SearchingMethod;
import org.springframework.beans.factory.annotation.Autowired;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;

public class StickerService implements ServiceInterface {

    private static final Logger logger = Logger.getLogger(StickerService.class.getName());
    private static final Random random = new Random(12345);

    @Autowired
    private StickerRepository repository;

    @Override
    @Transactional
    public Sticker save(Sticker sticker) {
        logger.info(String.format("Saved sticker of size %d, made by %s, pack: %s",
                sticker.getImage().length, sticker.getOwner(), sticker.getId()));
        return repository.save(sticker);
    }

    @Override
    public Optional<Sticker> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public long findNewestId() {
        return repository.findMaxId();
    }

    @Override
    public long findElementsNumber() {
        return repository.getElementsNumber();
    }

    @Override
    public long findPacksNumber() {
        return repository.getPacksNumber();
    }

    @Override
    public long findOwnersNumber() {
        return repository.getOwnersNumber();
    }

    @Override
    public List<Sticker> findByPack(String pack) {
        return repository.findByPack(pack);
    }

    @Override
    public List<Sticker> findByOwner(String owner) {
        return repository.findByOwner(owner);
    }

    @Override
    public List<Sticker> findBy(SearchingMethod method, String value) {
        return switch(method) {
            case OWNER -> findByOwner(value);
            case PACK -> findByPack(value);
        };
    }

    @Override
    public List<String> findAllOwners() {
        return repository.findAllOwners();
    }

    @Override
    public List<String> findAllPacks() {
        return repository.findAllPacks();
    }

}
