package me.pr0crustes.backend.classes.file;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Subclass of FileSelector, handles Single File Selection.
 */
public class SingleFileSelector extends FileSelector<File> {

    /**
     * Implementation of FileSelector getCallable.
     * @param stage the stage to show the window.
     * @param filters the filters to be used.
     * @return a callable that will return the selection.
     */
    @Override
    protected Callable<File> getCallable(Stage stage, List<FileChooser.ExtensionFilter> filters) {
        return (() -> this.createFileWindow("Select a file", filters).showOpenDialog(stage));
    }

}
