package com.pickominio.model;

public class FrozenDiceSet extends DiceSet {
    private FrozenDiceSet() {
        super();
    }

    public static DiceSet buildNewSet() {
        return new FrozenDiceSet();
    }
}
