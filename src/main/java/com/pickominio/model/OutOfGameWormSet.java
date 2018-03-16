package com.pickominio.model;

import java.util.TreeSet;

public class OutOfGameWormSet extends WormSet {
    private OutOfGameWormSet() {
        super();
        resetOutOfGameWormSet();
    }

    private void resetOutOfGameWormSet() {
        wormSet = new TreeSet<>();
    }

    public static OutOfGameWormSet build() {
        return new OutOfGameWormSet();
    }
}
