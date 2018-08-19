package me.pr0crustes.backend.classes.file;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Subclass of FileSelector, handles multiple File Selection.
 */
public class MultipleFileSelector extends FileSelector<List<File>> {

    /**
     * Implementation of FileSelector getCallable.
     * @param stage the stage to show the window.
     * @param filters the filters to be used.
     * @return a callable that will return the selection.
     */
    @Override
    protected Callable<List<File>> getCallable(Stage stage, List<FileChooser.ExtensionFilter> filters) {
        return (() -> createFileWindow("Select one or more files", filters).showOpenMultipleDialog(stage));
    }

}
