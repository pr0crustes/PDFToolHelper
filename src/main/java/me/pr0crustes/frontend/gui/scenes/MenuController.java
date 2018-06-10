package me.pr0crustes.frontend.gui.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import me.pr0crustes.frontend.gui.classes.SimpleController;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController extends SimpleController {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void onClickSplit(ActionEvent event) {
        this.loadNewFXML(event, Scenes.Split);
    }

    @FXML
    void onClickMerge(ActionEvent event) {
        this.loadNewFXML(event, Scenes.Merge);
    }

    @FXML
    void onClickQuality(ActionEvent event) {
        this.loadNewFXML(event, Scenes.Quality);
    }

    @FXML
    void onClickConvert(ActionEvent event) {
        this.loadNewFXML(event, Scenes.Convert);
    }

}
