package com.pickominio.model;

import java.util.LinkedHashSet;

public class PlayerWormSet extends WormSet {
    private PlayerWormSet() {
        super();
        resetPlayerWormSet();
    }

    public static PlayerWormSet build() {
        return new PlayerWormSet();
    }

    public Worm getNewestWorm() {
        Worm wormToReturn = wormSet.stream()
                .skip(wormSet.size() - 1)
                .findFirst()
                .get();
        return takeWorm(wormToReturn.getValue());
    }

    private void resetPlayerWormSet() {
        wormSet = new LinkedHashSet<Worm>();
    }
}
