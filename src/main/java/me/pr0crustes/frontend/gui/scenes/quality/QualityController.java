package me.pr0crustes.frontend.gui.scenes.quality;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import me.pr0crustes.backend.classes.FileExtensions;
import me.pr0crustes.backend.classes.FileSelector;
import me.pr0crustes.backend.classes.PDFQualityModifier;
import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import me.pr0crustes.frontend.gui.classes.ActionController;

import java.io.File;

public class QualityController extends ActionController {

    @FXML
    private TextField textfieldFile;

    @FXML
    private TextField textfieldDpi;

    private File selectedFile;

    @FXML
    void onClickSearch() {
        this.selectedFile = FileSelector.askForSelect(FileExtensions.PDF);

        String filePath = FileSelector.getFilePath(this.selectedFile);

        this.textfieldFile.setText(filePath);
    }

    @Override
    public void execute() throws ArgumentException, NoFileException, PermissionException {
        if (this.selectedFile == null) {
            throw new ArgumentException();
        }

        int dpi = this.getDpiField();

        File saveDestiny = FileSelector.showSavePdfFile();

        PDFQualityModifier qualityModifier = new PDFQualityModifier(this.selectedFile);

        qualityModifier.saveWithDPI(dpi, saveDestiny);
    }

    private int getDpiField() throws ArgumentException {
        try {
            return Integer.valueOf(this.textfieldDpi.getText());
        } catch (NumberFormatException e) {
            throw new ArgumentException();
        }
    }

}
