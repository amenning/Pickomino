package com.pickominio.model;

public class FrozenDiceSet extends DiceSet {
    private FrozenDiceSet() {
        super();
    }

    public static FrozenDiceSet buildNewSet() {
        return new FrozenDiceSet();
    }
}
