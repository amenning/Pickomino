package com.pickominio.service;

import com.pickominio.model.*;

import java.util.TreeSet;

public class ResolveBunk {
    private DiceSet activeDiceSet;
    private DiceSet frozenDiceSet;
    private GrillWormSet grillWormSet;
    private PlayerWormSet playerWormSet;
    private OutOfGameWormSet outOfGameWormSet;

    public ResolveBunk from(PlayerWormSet playerWormSet) {
        this.playerWormSet = playerWormSet;
        return this;
    }

    public ResolveBunk to(GrillWormSet grillWormSet) {
        this.grillWormSet = grillWormSet;
        return this;
    }

    public ResolveBunk withActiveDice(ActiveDiceSet activeDiceSet) {
        this.activeDiceSet = activeDiceSet;
        return this;
    }

    public ResolveBunk withFrozenDice(FrozenDiceSet frozenDiceSet) {
        this.frozenDiceSet = frozenDiceSet;
        return this;
    }

    public ResolveBunk withOutOfGameWormSet(OutOfGameWormSet outOfGameWormSet) {
        this.outOfGameWormSet = outOfGameWormSet;
        return this;
    }

    public void resolve() throws Exception {
        validate();

        Worm returningWorm;
        Worm bunkWorm;
        frozenDiceSet.resetDiceSet();
        activeDiceSet.resetDiceSet();

        if (!playerWormSet.isEmpty()) {
            returningWorm = playerWormSet.getNewestWorm();
            if (returningWorm.getValue() < grillWormSet.getHighestWormValue()) {
                bunkWorm = grillWormSet.removeHighestWormFromGrill();
                outOfGameWormSet.addWorm(bunkWorm);
            }
            grillWormSet.addWorm(returningWorm);
        }

        reset();
    }

    private void validate() throws Exception {
        if(activeDiceSet == null
            || frozenDiceSet == null
            || grillWormSet == null
            || playerWormSet == null
            || outOfGameWormSet == null) {
            throw new Exception(
                    "Must use from().to().withActiveDice().withFrozenDice().withOutOfGameWormSet().resolve() method call"
            );
        }
    }

    private void reset() {
        activeDiceSet = null;
        frozenDiceSet = null;
        grillWormSet = null;
        playerWormSet = null;
        outOfGameWormSet = null;
    }
}
