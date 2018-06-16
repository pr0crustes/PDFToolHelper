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
import me.pr0crustes.backend.classes.PDFCropper;
import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import me.pr0crustes.frontend.gui.classes.ActionController;
import me.pr0crustes.frontend.gui.classes.layout.NodesHelper;

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

    private int getStartPage() throws NumberFormatException {
        return Integer.valueOf(this.textFieldFromPage.getText());
    }

    private int getEndPage() throws NumberFormatException {
        return Integer.valueOf(this.textFieldToPage.getText());
    }

    public void execute() throws ArgumentException, NoFileException, PermissionException {
        if (this.selectedFile == null) {
            throw new ArgumentException();
        }

        File destinyFile = FileSelector.showSavePdfFile();

        PDFCropper cropper = new PDFCropper(this.selectedFile);

        cropper.cropDocument(this.getStartPage(), this.getEndPage(), destinyFile);
    }

    @Override
    public void setupGUI(Pane pane) {

        this.textFieldFile = new TextField();
        this.textFieldFile.setPrefWidth(300);
        this.textFieldFile.setFont(Font.font(10));

        this.textFieldFromPage = new TextField();
        this.textFieldFromPage.setPrefWidth(50);
        this.textFieldFromPage.setAlignment(Pos.CENTER);

        this.textFieldToPage = new TextField();
        this.textFieldToPage.setPrefWidth(50);
        this.textFieldToPage.setAlignment(Pos.CENTER);

        GridPane gridPaneFile = new GridPane();

        gridPaneFile.add(new Label("File:"), 0, 0);
        gridPaneFile.add(this.textFieldFile, 1, 0);

        Button buttonSelectFile = NodesHelper.buttonWithHandle("Select File", (event -> this.onClickSearch()));
        gridPaneFile.add(buttonSelectFile, 2, 0);

        gridPaneFile.setAlignment(Pos.CENTER);
        gridPaneFile.setHgap(10);
        gridPaneFile.setVgap(20);

        GridPane gridPaneCrop = new GridPane();

        gridPaneCrop.add(new Label("Start:"), 0, 0);
        gridPaneCrop.add(this.textFieldFromPage, 1, 0);

        gridPaneCrop.add(new Label("End:"), 0, 1);
        gridPaneCrop.add(this.textFieldToPage, 1, 1);

        gridPaneCrop.setAlignment(Pos.CENTER);
        gridPaneCrop.setHgap(10);
        gridPaneCrop.setVgap(20);

        Button buttonExecute = NodesHelper.buttonWithHandle("Save", super.eventDo());
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
