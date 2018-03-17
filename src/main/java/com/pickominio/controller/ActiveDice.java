package com.pickominio.controller;

import com.pickominio.model.DiceSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class ActiveDice {
    @FXML
    private HBox activeDiceBox;

    public void initialize() throws IOException {
        for (int count = 0; count < DiceSet.MAX_DICE_SET_SIZE; ++count) {
            addDiceToPane();
        }
    }

    private void addDiceToPane() throws IOException {
        StackPane dice = FXMLLoader.load(
            getClass().getResource("/fxml/dice.fxml")
        );
        dice.getChildren().get(0).setOnMouseClicked(e -> { freezeDice(); });
        activeDiceBox.getChildren().add(dice);
    }

    public void freezeDice() {
        System.out.println("Test Dice");
    }
}
