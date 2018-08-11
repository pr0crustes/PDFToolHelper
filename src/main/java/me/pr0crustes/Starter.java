package me.pr0crustes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import me.pr0crustes.frontend.gui.scene.menu.MenuController;

public class Starter extends Application {

    public static Stage mainStage; // Singleton

    private static final String programTitle = "PDF-Toolkit";

    @Override
    public void start(Stage primaryStage) {
        Starter.mainStage = primaryStage;

        AnchorPane root = new AnchorPane();

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle(Starter.programTitle);
        primaryStage.setResizable(false);
        primaryStage.show();

        new MenuController(root);
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
        launch(args);
    }
}
