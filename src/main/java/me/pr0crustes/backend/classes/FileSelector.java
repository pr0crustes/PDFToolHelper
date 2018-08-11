package me.pr0crustes.backend.classes;

import javafx.application.Platform;
import javafx.stage.FileChooser;
import me.pr0crustes.Starter;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.NoTargetFileException;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FileSelector {

    private static FileChooser createFileWindow(String title, FileChooser.ExtensionFilter[] filters) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(filters);
        return fileChooser;
    }

    private static File showFileSelectWindow(FileChooser.ExtensionFilter[] filters) {
        return FileSelector.createFileWindow("Select a file", filters).showOpenDialog(Starter.mainStage);
    }

    private static File showFileSaveWindow(FileChooser.ExtensionFilter[] filters) {
        return FileSelector.createFileWindow("Save as", filters).showSaveDialog(Starter.mainStage);
    }

    public static File askForSelect(FileExtensions... fileExtensions) {

        FileChooser.ExtensionFilter[] filters = ExtensionFilterFactory.combineFilters(fileExtensions);

        try {
            return FileSelector.runFileQuery(() -> FileSelector.showFileSelectWindow(filters));
        } catch (NoFileException e) {
            return null;
        }
    }

    public static File showSavePdfFile() throws NoFileException, NoTargetFileException {

        FileChooser.ExtensionFilter[] filters = {
                ExtensionFilterFactory.newFilter(FileExtensions.PDF)
        };

        File file = FileSelector.runFileQuery(() -> FileSelector.showFileSaveWindow(filters));

        if (file == null) {
            throw new NoTargetFileException();
        }
        return file;
    }

    private static File runFileQuery(Callable<File> callable) throws NoFileException {

        try {
            if (Platform.isFxApplicationThread()) {
                // Is FX thread

                return callable.call();

            } else {
                // Not the FX thread

                final FutureTask<File> query = new FutureTask<>(callable);

                Platform.runLater(query);

                return query.get();
            }
        } catch (Exception e) {
            throw new NoFileException();
        }
    }

    public static String getFilePath(File file) {
        return file == null ? "" : file.getPath();
    }
}
