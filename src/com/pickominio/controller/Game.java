package com.pickominio.controller;

import com.pickominio.model.ActiveDiceSet;
import com.pickominio.model.DiceSet;

public class Game {
    public Game() {
        DiceSet activeDiceSet = ActiveDiceSet.buildNewSet();
        System.out.println(activeDiceSet);
    }
}