package com.example.demo.datatypes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sticker {

    @JsonProperty("owner")
    public String owner;

    @JsonProperty("image")
    public byte[] sticker;

    public Sticker(String owner, byte[] sticker) {
        this.owner = owner;
        this.sticker = sticker;
    }

}
