package com.pickominio.controller;

import com.pickominio.model.ActiveDiceSet;
import com.pickominio.model.DiceSet;
import com.pickominio.service.Roll;

public class Game {
    public Game() {
        DiceSet activeDiceSet = ActiveDiceSet.buildNewSet();
        System.out.println(activeDiceSet);
        Roll roll = new Roll();
        roll.diceSet(activeDiceSet);
        System.out.println(activeDiceSet);
    }
}