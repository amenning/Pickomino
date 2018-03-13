package com.pickominio.model;

public class Dice {
    public static final int MIN_DICE_VALUE = 1;
    public static final int MAX_DICE_VALUE = 6;
    private int value;

    private Dice(int value) {
        this.value = value;
    }

    public static Dice buildDice(int value) {
        return new Dice(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
