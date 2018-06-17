package me.pr0crustes.frontend.gui.classes;

import javafx.scene.layout.Pane;

import java.util.Objects;

public abstract class PassiveController implements Setup {

    protected PassiveController(Pane pane) {
        Objects.requireNonNull(pane).getChildren().clear();
        this.setupGUI(pane);
    }

}
