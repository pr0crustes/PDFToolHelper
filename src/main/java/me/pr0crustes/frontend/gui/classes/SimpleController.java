package me.pr0crustes.frontend.gui.classes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class SimpleController extends FlowManager  {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void onClickBack(ActionEvent event) {
        this.goBack(event);
    }

    private void goBack(ActionEvent event) {
        this.loadNewFXML(event, Scenes.MainMenu);
    }
}
