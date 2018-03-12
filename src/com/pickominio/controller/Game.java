package com.pickominio.controller;

import com.pickominio.model.ActiveDiceSet;
import com.pickominio.model.Dice;
import com.pickominio.model.DiceSet;
import com.pickominio.model.FrozenDiceSet;
import com.pickominio.service.FreezeDice;
import com.pickominio.service.Roll;

import java.util.List;

public class Game {
    public Game() {
        DiceSet activeDiceSet = ActiveDiceSet.buildNewSet();
        DiceSet frozenDiceSet = FrozenDiceSet.buildNewSet();
        System.out.println("Start");
        System.out.println(activeDiceSet);
        System.out.println(frozenDiceSet);

        System.out.println("Roll");
        Roll roll = new Roll();
        roll.diceSet(activeDiceSet);
        System.out.println(activeDiceSet);

        System.out.println("Freeze Dice");
        FreezeDice freeze = new FreezeDice();
        try {
            freeze.from(activeDiceSet).to(frozenDiceSet).value(3);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(activeDiceSet);
        System.out.println(frozenDiceSet);

        System.out.println("Roll");
        roll.diceSet(activeDiceSet);
        System.out.println(activeDiceSet);

        System.out.println("Freeze Dice");
        try {
            freeze.from(activeDiceSet).to(frozenDiceSet).value(5);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(activeDiceSet);
        System.out.println(frozenDiceSet);
    }
}