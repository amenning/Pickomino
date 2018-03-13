package com.pickominio.model;

public class OutOfGameWormSet extends WormSet {
    private OutOfGameWormSet() {
        super();
    }

    public static OutOfGameWormSet build() {
        return new OutOfGameWormSet();
    }
}
