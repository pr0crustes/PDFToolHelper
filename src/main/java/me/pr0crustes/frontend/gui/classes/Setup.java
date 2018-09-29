package me.pr0crustes.frontend.gui.classes;

import javafx.scene.layout.Pane;

/**
 * Setup is a interface that forces whoever implements
 * into implementing 'setupGUI'.
 */
public interface Setup {

    /**
     * SetupGUI is a method that receives a Pane
     * and should create on it the necessary GUI.
     * @param pane the pane the GUI should be made on.
     */
    void setupGUI(Pane pane);

    /**
     * BeforeSetupGUI is a method that receives a Pane
     * but is called right before SetupGUI.
     * @param pane the pane passed on.
     */
    void beforeSetupGUI(Pane pane);

}
