package com.example.demo.services;

import com.example.demo.entities.Sticker;
import com.example.demo.repositories.StickerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

public class StickerServiceImp implements StickerService {

    @Autowired
    private StickerRepository repository;

    @Override
    @Transactional
    public Sticker save(Sticker sticker) {
        return repository.save(sticker);
    }

    @Override
    public List<Sticker> findByPack(String pack) {
        return repository.findByPack(pack);
    }

    @Override
    public List<Sticker> findByOwner(String owner) {
        return repository.findByOwner(owner);
    }
}
