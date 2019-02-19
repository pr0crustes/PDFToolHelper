package me.pr0crustes.backend.classes.file;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

/**
 * Subclass of FileSelector, handles Single File Selection.
 */
public class SingleFileSelector extends FileSelector<File> {

    /**
     * Implementation of FileSelector askForSelection.
     * @param stage the stage to show the window.
     * @param filters the filters to be used.
     * @return return the selection.
     */
    @Override
    protected File askForSelection(Stage stage, List<FileChooser.ExtensionFilter> filters) {
        return this.createFileWindow("Select a file", filters).showOpenDialog(stage);
    }

}
