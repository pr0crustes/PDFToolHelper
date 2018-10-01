package me.pr0crustes.frontend.gui.classes;

import javafx.scene.layout.Pane;

import java.util.Objects;

/**
 * PassiveController is an abstract class that implements Setup
 * but in it constructor clear the Pane and calls setupGUI in the same.
 * If a scene controller does not have an action (save) button, the controller should extend this.
 */
public abstract class PassiveController implements Setup {

    /**
     * Constructor that receiving a pane, clears it and calls
     * beforeSetupGUI followed by setupGUI.
     * @param pane the Pane that the GUI should be drawn on.
     */
    protected PassiveController(Pane pane) {
        Objects.requireNonNull(pane).getChildren().clear();
        this.setupGUI(pane);
    }

}
