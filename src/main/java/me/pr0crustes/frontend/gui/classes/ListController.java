package me.pr0crustes.frontend.gui.classes;

import javafx.scene.layout.Pane;

import java.io.File;

/**
 * ListController is an abstract class that extends ActionController
 * but forces the implementation of 'addNewFileToList', a method that
 * class can depend on.
 */
public abstract class ListController extends ActionController {

    /**
     * Just a constructor to call super.
     * @param pane the Pane to call setupGUI on.
     */
    protected ListController(Pane pane) {
        super(pane);
    }

    /**
     * Abstract method, should return a File when called.
     * @return a File that should be added to a list.
     */
    public abstract File addNewFileToList();
}
