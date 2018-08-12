package me.pr0crustes.backend.classes;

import javafx.application.Platform;
import javafx.stage.FileChooser;
import me.pr0crustes.Starter;
import me.pr0crustes.backend.enums.FileExtensions;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.NoTargetFileException;

import java.io.File;
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
    private static FileChooser createFileWindow(String title, FileChooser.ExtensionFilter[] filters) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(filters);
        return fileChooser;
    }

    /**
     * Method that shows a file select window.
     * @param filters the filter to be used.
     * @return the File selected. Can be null if the user cancels.
     */
    private static File showFileSelectWindow(FileChooser.ExtensionFilter[] filters) {
        return FileSelector.createFileWindow("Select a file", filters).showOpenDialog(Starter.mainStage);
    }

    /**
     * Method that shows a file save window.
     * @param filters the filter to be used.
     * @return the File to be save as. Can be null if the user cancels.
     */
    private static File showFileSaveWindow(FileChooser.ExtensionFilter[] filters) {
        return FileSelector.createFileWindow("Save as", filters).showSaveDialog(Starter.mainStage);
    }

    /**
     * Method that receives N FileExtensions, asking for a file to be selected.
     * @param fileExtensions N FIleExtensions to be used.
     * @return the selected File.
     */
    public static File askForSelect(FileExtensions... fileExtensions) {
        FileChooser.ExtensionFilter[] filters = ExtensionFilterFactory.combineFilters(fileExtensions);
        try {
            return FileSelector.runFileQuery(() -> FileSelector.showFileSelectWindow(filters));
        } catch (NoFileException e) {
            return null;
        }
    }

    /**
     * Method that shows the savePdf window.
     * @return the saveAs File the user selected.
     * @throws NoFileException in case there is a problem reading the file the user selected.
     * @throws NoTargetFileException in case the user cancels the action.
     */
    public static File showSavePdfFile() throws NoFileException, NoTargetFileException {
        FileChooser.ExtensionFilter[] filters = {
                FileExtensions.PDF.asFilter()
        };

        File file = FileSelector.runFileQuery(() -> FileSelector.showFileSaveWindow(filters));

        if (file == null) {
            throw new NoTargetFileException();
        }
        return file;
    }

    /**
     * Method that is a wrapper, calling in the FXThread.
     * @param callable a callable to be call.
     * @return the selected File.
     * @throws NoFileException in case there is a problem reading the file the user selected.
     */
    private static File runFileQuery(Callable<File> callable) throws NoFileException {
        try {
            if (Platform.isFxApplicationThread()) {
                return callable.call();
            } else {
                final FutureTask<File> query = new FutureTask<>(callable);
                Platform.runLater(query);
                return query.get();
            }
        } catch (Exception e) {
            throw new NoFileException();
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
