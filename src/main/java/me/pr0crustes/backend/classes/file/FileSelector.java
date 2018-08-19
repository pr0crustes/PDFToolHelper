package me.pr0crustes.backend.classes.file;

import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import me.pr0crustes.Starter;
import me.pr0crustes.backend.enums.FileExtensions;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Base class for all FileSelectors.
 */
public abstract class FileSelector <T> {

    /**
     * Method that subclasses must implement.
     * @return a callable with return T, to get the selection.
     */
    protected abstract Callable<T> getCallable(Stage stage, List<FileChooser.ExtensionFilter> filters);

    /**
     * Method that returns the selection.
     * @return the selection with type T.
     */
    public T getSelection(FileExtensions... fileExtensions) {
        List<FileChooser.ExtensionFilter> filters = ExtensionFilterFactory.combineFilters(fileExtensions);
        return this.runFileQuery(this.getCallable(Starter.mainStage, filters));
    }

    /**
     * Method that easily creates a FileChooser.
     * @param title the FileChooser title.
     * @param filters the FileChooser extension filter.
     * @return a FileChooser as desired.
     */
    FileChooser createFileWindow(String title, List<FileChooser.ExtensionFilter> filters) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(filters);
        return fileChooser;
    }

    /**
     * Method that is a wrapper, calling in the FXThread.
     * @param callable a callable to be call.
     * @return the callable result.
     */
    private T runFileQuery(Callable<T> callable) {

        try {
            if (Platform.isFxApplicationThread()) {
                return callable.call();
            } else {
                final FutureTask<T> query = new FutureTask<>(callable);
                Platform.runLater(query);
                return query.get();
            }
        } catch (Exception e) {  // User canceled the action or there was a problem with the future task.
            return null;
        }
    }

    /**
     * A wrapper to get a File path, returning empty in null case.
     * @param file the file.
     * @return the file path. Returns an empty string if the file is null.
     */
    public static String getFilePath(File file) {
        return file == null ? "" : file.getPath();
    }


}
