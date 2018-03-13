package com.pickominio.model;

public class Worm implements Comparable<Worm> {
    private static final int WORM_COUNT_INCREMENT = 4;
    public static final int MIN_WORM_TILE = 21;
    public static final int MAX_WORM_TILE = 36;
    private int value;
    private int wormCount;
    private boolean outOfGame = false;

    private Worm(int value) {
        this.value = value;
        wormCount = ((value - MIN_WORM_TILE)/WORM_COUNT_INCREMENT) + 1;
    }

    public static Worm build(int value) {
        return new Worm(value);
    }

    public int getValue() {
        return value;
    }

    public int getWormCount() {
        return wormCount;
    }

    public boolean isOutOfGame() {
        return outOfGame;
    }

    public void setOutOfGame(boolean outOfGame) {
        this.outOfGame = outOfGame;
    }


    @Override
    public int compareTo(Worm other) {
        if (equals(other)) {
            return 0;
        }
        return Integer.compare(value, other.getValue());
    }

    @Override
    public String toString() {
        return "Worm{" +
                "value=" + value +
                ", wormCount=" + wormCount +
                '}';
    }
}
