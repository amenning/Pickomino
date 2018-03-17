package com.pickominio.controller;

import com.pickominio.model.DiceSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class ActiveDice {
    @FXML
    private HBox activeDiceBox;

    public void initialize() throws IOException {
        for (int count = 0; count < DiceSet.MAX_DICE_SET_SIZE; ++count) {
            StackPane dice = FXMLLoader.load(
                getClass().getResource("/fxml/dice.fxml")
            );
            activeDiceBox.getChildren().add(dice);
        }
    }

    public void freezeDice(ActionEvent actionEvent) {
        System.out.println("Test Dice");
    }
}
