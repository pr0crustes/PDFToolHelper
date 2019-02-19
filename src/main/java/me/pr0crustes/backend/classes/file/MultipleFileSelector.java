package me.pr0crustes.backend.classes.file;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

/**
 * Subclass of FileSelector, handles multiple File Selection.
 */
public class MultipleFileSelector extends FileSelector<List<File>> {

    /**
     * Implementation of FileSelector askForSelection.
     * @param stage the stage to show the window.
     * @param filters the filters to be used.
     * @return the selection.
     */
    @Override
    protected List<File> askForSelection(Stage stage, List<FileChooser.ExtensionFilter> filters) {
        return createFileWindow("Select one or more files", filters).showOpenMultipleDialog(stage);
    }

}
