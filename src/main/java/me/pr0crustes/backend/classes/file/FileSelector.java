package me.pr0crustes.backend.classes.file;

import javafx.application.Platform;
import javafx.stage.FileChooser;
import me.pr0crustes.Starter;
import me.pr0crustes.backend.enums.FileExtensions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Class made of static methods that handles a lot of file selecting stuff.
 */
public class FileSelector {

    /**
     * Method that easily creates a FileChooser.
     * @param title the FileChooser title.
     * @param filters the FileChooser extension filter.
     * @return a FileChooser as desired.
     */
    private static FileChooser createFileWindow(String title, List<FileChooser.ExtensionFilter> filters) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(filters);
        return fileChooser;
    }

    /**
     * Method that receives N FileExtensions, asking for a file to be selected.
     * @param fileExtensions N FIleExtensions to be used.
     * @return the selected File.
     */
    public static File askForSingleFile(FileExtensions... fileExtensions) {
        List<FileChooser.ExtensionFilter> filters = ExtensionFilterFactory.combineFilters(fileExtensions);
        return FileSelector.runFileQuery(() ->
                FileSelector.createFileWindow("Select a file", filters).showOpenDialog(Starter.mainStage)
        );
    }

    /**
     * Method that receives N FileExtensions, asking for one or more files to be selected.
     * @param fileExtensions N FIleExtensions to be used.
     * @return list with the selected Files.
     */
    public static List<File> askForMultipleFile(FileExtensions... fileExtensions) {
        List<FileChooser.ExtensionFilter> filters = ExtensionFilterFactory.combineFilters(fileExtensions);
        return FileSelector.runFileQuery(() ->
                FileSelector.createFileWindow("Select one or more files", filters).showOpenMultipleDialog(Starter.mainStage)
        );
    }

    /**
     * Method that shows the savePdf window.
     * @return the saveAs File the user selected.
     */
    public static File showSavePdfFile() {
        List<FileChooser.ExtensionFilter> filters = new ArrayList<>();
        filters.add(FileExtensions.PDF.asFilter());

        return FileSelector.runFileQuery(() ->
                FileSelector.createFileWindow("Save as", filters).showSaveDialog(Starter.mainStage)
        );
    }

    /**
     * Method that is a wrapper, calling in the FXThread.
     * @param callable a callable to be call.
     * @return the callable result.
     */
    private static <T> T runFileQuery(Callable<T> callable) {

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
