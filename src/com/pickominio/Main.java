package com.pickominio;

import com.pickominio.model.Dice;
import com.pickominio.model.Grill;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void init() throws Exception {
        Thread.sleep(3000);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass()
                .getResource("/fxml/gameboard.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        //primaryStage.show();
    }

	public static void main (String[] args) {
        LauncherImpl.launchApplication(Main.class, WelcomeSplash.class, args);
	}
}