package com.example.demo.repositories;

import com.example.demo.entities.Sticker;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface StickerRepository extends CrudRepository<Sticker, Long> {
    List<Sticker> findByPack(String pack);
    List<Sticker> findByOwner(String owner);
}
