package me.pr0crustes.backend.classes.file;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import me.pr0crustes.backend.enums.FileExtensions;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Subclass of FileSelector, handles the save as selection.
 */
public class SaveFileSelector extends FileSelector<File> {

    /**
     * Implementation of FileSelector getCallable.
     * @param stage the stage to show the window.
     * @param filters the filters to be used.
     * @return a callable that will return the selection.
     */
    @Override
    protected Callable<File> getCallable(Stage stage, List<FileChooser.ExtensionFilter> filters) {
        // Add PDF extension as first, making it is the default.
        filters.add(0, FileExtensions.PDF.asFilter());
        return (() -> this.createFileWindow("Save as", filters).showSaveDialog(stage));
    }

}
