package com.pickominio.model;

import java.util.TreeSet;

public class GrillWormSet extends WormSet {
    private GrillWormSet() {
        super();
        resetGrillWormSet();
    }

    public static GrillWormSet build() {
        return new GrillWormSet();
    }

    private void resetGrillWormSet() {
        wormSet = new TreeSet<>();
        for (int value = Worm.MIN_WORM_TILE; value <= Worm.MAX_WORM_TILE; ++value) {
            wormSet.add(Worm.build(value));
        }
    }

    public int getHighestWormValue() {
        TreeSet<Worm> sortedSet = (TreeSet) wormSet;
        return sortedSet.last().getValue();
    }

    public Worm removeHighestWormFromGrill() {
        TreeSet<Worm> sortedSet = (TreeSet) wormSet;
        return sortedSet.pollLast();
    }
}
