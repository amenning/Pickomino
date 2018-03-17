package com.pickominio.controller;

import com.pickominio.service.Roll;
import javafx.event.ActionEvent;

public class PlayerOption {
    private Roll roll;

    public void rollDice(ActionEvent actionEvent) {
        System.out.println("Test Roll");
        roll.diceSet();
    }

    public void registerRollService(Roll roll) {
        this.roll = roll;
    }
}
