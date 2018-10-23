package me.pr0crustes.backend.classes.file;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import me.pr0crustes.backend.enums.FileExtensions;
import me.pr0crustes.backend.exeptions.NullFileException;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Subclass of FileSelector, handles the save as selection.
 */
public class SaveFileSelector extends FileSelector<File> {

    /**
     * Implementation of FileSelector askForSelection.
     * @param stage the stage to show the window.
     * @param filters the filters to be used.
     * @return the selection.
     */
    @Override
    protected File askForSelection(Stage stage, List<FileChooser.ExtensionFilter> filters) {
        // Add PDF extension as first, making it the default.
        filters.add(0, FileExtensions.PDF.asFilter());
        return this.createFileWindow("Save as", filters).showSaveDialog(stage);
    }

    /**
     * Method that asks the user and saves a given PDDocument.
     * @param document the PDDocument to be saved.
     * @throws IOException in case of an exception during file saving.
     * @throws NullFileException in case the user selected file is null.
     */
    public void savePDF(PDDocument document) throws IOException, NullFileException {
        File saveAs = this.getSelection();

        if (saveAs == null) {
            throw new NullFileException();
        }

        document.save(saveAs);
    }

}
