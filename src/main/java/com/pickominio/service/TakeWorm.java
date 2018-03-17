package com.pickominio.service;

import com.pickominio.exception.ValueAlreadyFrozenException;
import com.pickominio.exception.ValueMissingException;
import com.pickominio.model.Worm;
import com.pickominio.model.WormSet;

public class TakeWorm {
    private final ResetDiceSets resetDiceSets;
    private WormSet originatingWormSet;
    private WormSet destinationWormSet;
    private int wormValue;

    private TakeWorm(ResetDiceSets resetDiceSets) {
        this.resetDiceSets = resetDiceSets;
    }

    public static TakeWorm buildWithResetDiceService(ResetDiceSets resetDiceSets) {
        return new TakeWorm(resetDiceSets);
    }

    public TakeWorm from(WormSet originatingWormSet) {
        this.originatingWormSet = originatingWormSet;
        return this;
    }

    public TakeWorm to(WormSet destinationWormSet) {
        this.destinationWormSet = destinationWormSet;
        return this;
    }

    public TakeWorm wormValue(int value) {
        this.wormValue = value;
        return this;
    }

    public void resolve() throws Exception {
        validate();

        Worm worm = originatingWormSet.takeWorm(wormValue);
        destinationWormSet.addWorm(worm);
        resetDiceSets.reset();

        reset();
    }

    private void validate() throws Exception {
        if(originatingWormSet == null
            || destinationWormSet == null
            || wormValue == 0) {
            throw new Exception("Must use wormValue().from().to().resolve() method call");
        }

        if (!originatingWormSet.hasWorm(wormValue)) {
            throw new ValueMissingException();
        }
    }

    private void reset() {
        originatingWormSet = null;
        destinationWormSet = null;
        wormValue = 0;
    }
}
