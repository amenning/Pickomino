package com.pickominio;

import com.pickominio.legacyController.LegacyGame;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void init() throws Exception {
        // Welcome splash pause
        Thread.sleep(3000);
        new LegacyGame();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass()
                .getResource("/fxml/gameboard.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(Main.class, WelcomeSplash.class, args);
    }
}