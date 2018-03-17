package com.pickominio.controller;

import com.pickominio.model.*;
import com.pickominio.service.FreezeDice;
import com.pickominio.service.ResetDiceSets;
import com.pickominio.service.Roll;
import javafx.fxml.FXML;
import javafx.stage.Window;

public class Game {
    private final ActiveDiceSet activeDiceSet;
    private final FrozenDiceSet frozenDiceSet;
    private final GrillWormSet grillWormSet;
    private final OutOfGameWormSet outOfGameWormSet;
    private final PlayerWormSet playerWormSet;
    private final ResetDiceSets resetDiceSets;
    private final Roll roll;

    @FXML
    private Window playerOptionsWindow;
    @FXML
    private PlayerOption playerOptionController;

    public Game() {
        grillWormSet = GrillWormSet.build();
        outOfGameWormSet = OutOfGameWormSet.build();
        activeDiceSet = ActiveDiceSet.buildNewSet();
        frozenDiceSet = FrozenDiceSet.buildNewSet();
        playerWormSet = PlayerWormSet.build();

        resetDiceSets = ResetDiceSets.build()
            .registerActiveDiceSet(activeDiceSet)
            .registerFrozenDiceSet(frozenDiceSet);

        roll = Roll.buildWithActiveDiceSet(activeDiceSet);
    }

    public void initialize() {
        playerOptionController.registerRollService(roll);
    }
}