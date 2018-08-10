package me.pr0crustes.frontend.gui.scene.crop;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import me.pr0crustes.backend.classes.FileExtensions;
import me.pr0crustes.backend.classes.FileSelector;
import me.pr0crustes.backend.classes.Numbers;
import me.pr0crustes.backend.classes.PDFCropper;
import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import me.pr0crustes.frontend.gui.classes.ActionController;
import me.pr0crustes.frontend.gui.classes.layout.NodeFactory;

import java.io.File;

public class CropController extends ActionController {

    private File selectedFile;

    private TextField textFieldFile;

    private TextField textFieldFromPage;

    private TextField textFieldToPage;

    public CropController(Pane pane) {
        super(pane);
    }

    private void onClickSearch() {

        this.selectedFile = FileSelector.askForSelect(FileExtensions.PDF);

        String filePath = FileSelector.getFilePath(this.selectedFile);

        this.textFieldFile.setText(filePath);
    }

    public void execute() throws ArgumentException, NoFileException, PermissionException {
        if (this.selectedFile == null) {
            throw new ArgumentException();
        }

        File destinyFile = FileSelector.showSavePdfFile();

        PDFCropper cropper = new PDFCropper(this.selectedFile);

        cropper.cropDocument(Numbers.valueFromTextField(this.textFieldFromPage), Numbers.valueFromTextField(this.textFieldToPage), destinyFile);
    }

    @Override
    public void setupGUI(Pane pane) {

        this.textFieldFile = NodeFactory.textFieldWithWidthAndAlignment(300, Pos.CENTER);

        this.textFieldFromPage = NodeFactory.textFieldWithWidthAndAlignment(50, Pos.CENTER);

        this.textFieldToPage = NodeFactory.textFieldWithWidthAndAlignment(50, Pos.CENTER);

        GridPane gridPaneFile = NodeFactory.gridPaneWithProperties(Pos.CENTER, 10, 20);

        gridPaneFile.add(new Label("File:"), 0, 0);
        gridPaneFile.add(this.textFieldFile, 1, 0);

        Button buttonSelectFile = NodeFactory.buttonWithHandle("Select File", (event -> this.onClickSearch()));
        gridPaneFile.add(buttonSelectFile, 2, 0);

        GridPane gridPaneCrop = NodeFactory.gridPaneWithProperties(Pos.CENTER, 10, 20);

        gridPaneCrop.add(new Label("Start:"), 0, 0);
        gridPaneCrop.add(this.textFieldFromPage, 1, 0);

        gridPaneCrop.add(new Label("End:"), 0, 1);
        gridPaneCrop.add(this.textFieldToPage, 1, 1);

        Button buttonExecute = NodeFactory.buttonWithHandle("Save", super.eventDo());
        buttonExecute.setDefaultButton(true);

        VBox vBox = new VBox(
                gridPaneFile,
                gridPaneCrop,
                buttonExecute
        );

        vBox.setAlignment(Pos.CENTER);

        vBox.setSpacing(30);

        pane.getChildren().add(vBox);
    }
}
