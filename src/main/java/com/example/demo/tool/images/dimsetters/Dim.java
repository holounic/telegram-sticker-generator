package com.example.demo.tool.images.dimsetters;

import java.util.function.IntBinaryOperator;

public class Dim {
    public final int width, height;

    public Dim(int width, int height, IntBinaryOperator minSetter, IntBinaryOperator maxSetter) {
        if (width > height) {
            this.width = maxSetter.applyAsInt(width, height);
            this.height = minSetter.applyAsInt(width, height);
        } else {
            this.height = maxSetter.applyAsInt(width, height);
            this.width = minSetter.applyAsInt(width, height);
        }
    }
}
