package me.pr0crustes.frontend.gui.scenes.split;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import me.pr0crustes.backend.classes.FileExtensions;
import me.pr0crustes.backend.classes.FileSelector;
import me.pr0crustes.backend.classes.PDFCropper;
import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import me.pr0crustes.frontend.gui.classes.ActionController;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SplitController extends ActionController {

    private File selectedFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField textfieldFile;

    @FXML
    private TextField textfieldFromPage;

    @FXML
    private TextField textfieldToPage;

    @FXML
    void onClickSearch() {

        this.selectedFile = FileSelector.askForSelect(FileExtensions.PDF);

        String filePath = FileSelector.getFilePath(this.selectedFile);

        this.textfieldFile.setText(filePath);
    }

    private int getStartPage() throws NumberFormatException {
        return Integer.valueOf(this.textfieldFromPage.getText());
    }

    private int getEndPage() throws NumberFormatException {
        return Integer.valueOf(this.textfieldToPage.getText());
    }

    public void execute() throws ArgumentException, NoFileException, PermissionException {
        if (this.selectedFile == null) {
            throw new ArgumentException();
        }

        File destinyFile = FileSelector.showSavePdfFile();

        PDFCropper cropper = new PDFCropper(this.selectedFile);

        cropper.cropDocument(this.getStartPage(), this.getEndPage(), destinyFile);
    }

}
