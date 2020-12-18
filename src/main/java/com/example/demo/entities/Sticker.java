package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sticker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String owner;
    private String pack;
    private byte[] sticker;

    public Sticker(String owner, String pack, byte[] sticker) {
        this.owner = owner;
        this.pack = pack;
        this.sticker = sticker;
    }

    public Long getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getPack() {
        return pack;
    }

    public byte[] getSticker() {
        return sticker;
    }
}
