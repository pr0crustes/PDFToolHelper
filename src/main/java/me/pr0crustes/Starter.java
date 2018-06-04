package me.pr0crustes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.pr0crustes.frontend.gui.classes.FlowManager;

public class Starter extends Application {

    public static Stage mainStage; // Singleton

    public static String programTitle = "PDF-Toolbox";

    @Override
    public void start(Stage primaryStage) throws Exception {

        Starter.mainStage = primaryStage;

        String viewPath = FlowManager.Scenes.MainMenu.getViewPath();
        Parent root = FXMLLoader.load(getClass().getResource(viewPath));
        primaryStage.setTitle(Starter.programTitle);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
