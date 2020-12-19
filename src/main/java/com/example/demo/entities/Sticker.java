package com.example.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "sticker")
public class Sticker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "owner", columnDefinition = "VARCHAR(128)")
    private String owner;

    @Column(name = "pack", columnDefinition = "VARCHAR(128)")
    private String pack;

    @Lob
    @Column(name = "image", length = Integer.MAX_VALUE)
    private byte[] image;

    public Sticker(String owner, String pack, byte[] sticker) {
        this.owner = owner;
        this.pack = pack;
        this.image = sticker;
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

    public byte[] getImage() {
        return image;
    }
}
