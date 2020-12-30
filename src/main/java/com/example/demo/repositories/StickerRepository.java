package com.example.demo.repositories;

import com.example.demo.entities.Sticker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface StickerRepository extends CrudRepository<Sticker, Long> {
    @Query(value = "SELECT DISTINCT owner FROM sticker", nativeQuery = true)
    List<String> findAllOwners();

    @Query(value = "SELECT DISTINCT pack FROM sticker", nativeQuery = true)
    List<String> findAllPacks();

    @Query(value = "SELECT MAX(ID) FROM sticker", nativeQuery = true)
    long findMaxId();

    @Query(value = "SELECT COUNT(*) FROM sticker", nativeQuery = true)
    long getElementsNumber();

    @Query(value = "SELECT COUNT(DISTINCT pack) FROM sticker", nativeQuery = true)
    long getPacksNumber();

    @Query(value = "SELECT COUNT(DISTINCT owner) FROM sticker", nativeQuery = true)
    long getOwnersNumber();

    List<Sticker> findByPack(String pack);
    List<Sticker> findByOwner(String owner);
    Optional<Sticker> findById(long id);

}
