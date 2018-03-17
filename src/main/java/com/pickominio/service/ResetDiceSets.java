package com.pickominio.service;

import com.pickominio.model.DiceSet;

public class ResetDiceSets {
    private DiceSet activeDiceSet;
    private DiceSet frozenDiceSet;

    private ResetDiceSets() {}

    public static ResetDiceSets build() {
        return new ResetDiceSets();
    }

    public ResetDiceSets registerActiveDiceSet(DiceSet activeDiceSet) {
        this.activeDiceSet = activeDiceSet;
        return this;
    }

    public ResetDiceSets registerFrozenDiceSet(DiceSet frozenDiceSet) {
        this.frozenDiceSet = frozenDiceSet;
        return this;
    }

    public void reset() throws Exception {
        validate();

        activeDiceSet.resetDiceSet();
        frozenDiceSet.resetDiceSet();
    }

    private void validate() throws Exception {
        if(activeDiceSet == null || frozenDiceSet == null) {
            throw new Exception(
                "Must register active and frozen dice sets first"
            );
        }
    }
}
