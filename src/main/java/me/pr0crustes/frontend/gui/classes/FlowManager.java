package me.pr0crustes.frontend.gui.classes;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.pr0crustes.Starter;

import java.io.IOException;

public abstract class FlowManager implements Initializable {

    public enum Scenes {

        MainMenu(""),

        Merge("merge/"),
        Quality("quality/"),
        Split("split/");


        private String value;

        Scenes(String value) {
            this.value = value;
        }

        public String getViewPath() {
            return FlowManager.scenesPath + this.value + "View.fxml";
        }
    }

    private static String scenesPath = "/me/pr0crustes/frontend/gui/scenes/";

    protected void loadNewFXML(ActionEvent event, Scenes scene) {
        this.loadView(event, scene.getViewPath(), Starter.programTitle);
    }

    private void loadView(ActionEvent event, String path, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(title);
            stage.centerOnScreen();
            stage.show();
            // Hide this current window
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace(); // AKA ignore
        }
    }
}
