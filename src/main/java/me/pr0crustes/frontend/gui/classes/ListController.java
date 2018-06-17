package me.pr0crustes.frontend.gui.classes;

import javafx.scene.layout.Pane;

import java.io.File;

public abstract class ListController extends ActionController {

    protected ListController(Pane pane) {
        super(pane);
    }

    public abstract File addNewFileToList();
}
