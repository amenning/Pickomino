package com.pickominio.controller;

import com.pickominio.model.*;
import com.pickominio.service.FreezeDice;
import com.pickominio.service.Roll;

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

        GrillWormSet grillWormSet = GrillWormSet.build();
        System.out.println(grillWormSet);
        System.out.println("Has value 20? " + grillWormSet.hasWorm(20));
        System.out.println("Has value 30? " + grillWormSet.hasWorm(30));
        System.out.println("Has value 40? " + grillWormSet.hasWorm(40));

        Worm worm = grillWormSet.takeWorm(30);
        System.out.println(grillWormSet);

        grillWormSet.addWorm(worm);
        System.out.println(grillWormSet);

        worm = grillWormSet.removeHighestWormFromGrill();
        System.out.println(grillWormSet);
        OutOfGameWormSet deadWorms = OutOfGameWormSet.build();
        deadWorms.addWorm(worm);
        System.out.println(deadWorms);
    }
}