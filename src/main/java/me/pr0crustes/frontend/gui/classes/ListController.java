package me.pr0crustes.frontend.gui.classes;

import javafx.scene.layout.Pane;
import me.pr0crustes.backend.classes.file.MultipleFileSelector;

import java.io.File;
import java.util.List;

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
     * Method, should return an Array of Files when called.
     * Implemented by default but can be override.
     * @return a List of File that should be added to a list.
     */
    public List<File> addNewFilesToList() {
        return new MultipleFileSelector().getSelection();
    }

}
