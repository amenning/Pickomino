package com.pickominio;

import javafx.application.Preloader;
import javafx.application.Preloader.StateChangeNotification.Type;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WelcomeSplash extends Preloader {
    private Stage preloaderStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;

        Parent root = FXMLLoader.load(getClass()
                .getResource("/fxml/welcome.fxml"));
        primaryStage.setScene(new Scene(root, 525, 525));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    @Override
    public void handleStateChangeNotification(
            StateChangeNotification stateChangeNotification
    ) {
        if (stateChangeNotification.getType() == Type.BEFORE_START) {
            preloaderStage.hide();
        }
    }
}
