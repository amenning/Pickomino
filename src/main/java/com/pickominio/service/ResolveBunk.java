package com.pickominio.service;

import com.pickominio.model.*;

import java.util.TreeSet;

public class ResolveBunk {
    private final ResetDiceSets resetDiceSets;
    private DiceSet activeDiceSet;
    private DiceSet frozenDiceSet;
    private GrillWormSet grillWormSet;
    private PlayerWormSet playerWormSet;
    private OutOfGameWormSet outOfGameWormSet;

    private ResolveBunk(ResetDiceSets resetDiceSets) {
        this.resetDiceSets = resetDiceSets;
    }

    public static ResolveBunk buildWithResetDiceService(ResetDiceSets resetDiceSets) {
        return new ResolveBunk(resetDiceSets);
    }

    public ResolveBunk from(PlayerWormSet playerWormSet) {
        this.playerWormSet = playerWormSet;
        return this;
    }

    public ResolveBunk to(GrillWormSet grillWormSet) {
        this.grillWormSet = grillWormSet;
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
        resetDiceSets.reset();

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
        if(grillWormSet == null
            || playerWormSet == null
            || outOfGameWormSet == null) {
            throw new Exception(
                    "Must use from().to().withOutOfGameWormSet().resolve() method call"
            );
        }
    }

    private void reset() {
        grillWormSet = null;
        playerWormSet = null;
        outOfGameWormSet = null;
    }
}
