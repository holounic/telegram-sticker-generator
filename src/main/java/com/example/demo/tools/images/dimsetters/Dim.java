package com.example.demo.tools.images.dimsetters;

public class Dim {
    public final int width, height;

    public Dim(int width, int height, SideSetter minSetter, SideSetter maxSetter) {
        if (width > height) {
            this.width = maxSetter.set(width, height);
            this.height = minSetter.set(width, height);
        } else {
            this.height = maxSetter.set(width, height);
            this.width = minSetter.set(width, height);
        }
    }
}
