package com.example.demo.services;

import com.example.demo.entities.Sticker;
import com.example.demo.repositories.StickerRepository;
import com.example.demo.tools.gallery.SearchingMethod;
import org.springframework.beans.factory.annotation.Autowired;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class StickerService implements ServiceInterface {

    private static final Logger logger = Logger.getLogger(StickerService.class.getName());

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
