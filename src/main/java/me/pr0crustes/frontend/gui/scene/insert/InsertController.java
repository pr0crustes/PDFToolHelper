package me.pr0crustes.frontend.gui.scene.insert;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import me.pr0crustes.backend.classes.*;
import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import me.pr0crustes.frontend.gui.classes.ActionController;
import me.pr0crustes.frontend.gui.classes.layout.NodeFactory;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;

public class InsertController  extends ActionController {

    private File insertFile;
    private File intoFile;

    private TextField textFieldInsertFile;
    private CheckBox checkBoxAllFile;
    private TextField textFieldInsertFromPage;
    private TextField textFieldInsertToPage;

    private TextField textFieldIntoFile;
    private TextField textFieldIntoAfterPage;

    public InsertController(Pane pane) {
        super(pane);
    }

    public void execute() throws ArgumentException, NoFileException, PermissionException {
        if (this.insertFile == null || this.intoFile == null) {
            throw new ArgumentException();
        }

        int fromPage = 0;
        int toPage = 0;

        boolean allFile = this.checkBoxAllFile.isSelected();

        if (!allFile) {
            fromPage = Numbers.valueFromTextField(this.textFieldInsertFromPage);
            toPage = Numbers.valueFromTextField(this.textFieldInsertToPage);
        }

        int afterPage;

        try {
            afterPage = Numbers.valueFromTextField(this.textFieldIntoAfterPage);
        } catch (NumberFormatException e) {
            throw new ArgumentException();
        }

        File saveAs = FileSelector.showSavePdfFile();

        PDFInsert pdfInsert = new PDFInsert(this.insertFile, this.intoFile);

        PDDocument document = pdfInsert.insertDocument(allFile, fromPage, toPage, afterPage);

        PDFManager.saveAs(document, saveAs);
    }

    private void onClickInsertFileSearch() {
        this.insertFile = FileSelector.askForSelect(FileExtensions.PDF);
        this.textFieldInsertFile.setText(FileSelector.getFilePath(this.insertFile));
    }

    private void onClickIntoFileSearch() {
        this.intoFile = FileSelector.askForSelect(FileExtensions.PDF);
        this.textFieldIntoFile.setText(FileSelector.getFilePath(this.intoFile));
    }

    @Override
    public void setupGUI(Pane pane) {

        this.textFieldInsertFile = NodeFactory.textFieldWithWidthAndAlignment(200, Pos.CENTER_LEFT);
        this.textFieldInsertFile.setFont(Font.font(10));

        this.textFieldInsertFromPage = NodeFactory.textFieldWithWidthAndAlignment(50, Pos.CENTER);
        this.textFieldInsertToPage = NodeFactory.textFieldWithWidthAndAlignment(50, Pos.CENTER);

        this.checkBoxAllFile = new CheckBox();
        this.checkBoxAllFile.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.textFieldInsertFromPage.setDisable(newValue);
            this.textFieldInsertToPage.setDisable(newValue);
        });

        this.textFieldIntoFile = NodeFactory.textFieldWithWidthAndAlignment(200, Pos.CENTER_LEFT);
        this.textFieldIntoFile.setFont(Font.font(10));
        this.textFieldIntoAfterPage = NodeFactory.textFieldWithWidthAndAlignment(50, Pos.CENTER);


        GridPane gridPaneInsertFile = NodeFactory.gridPaneWithProperties(Pos.CENTER, 10, 20);

        gridPaneInsertFile.add(new Label("File:"), 0, 0);
        gridPaneInsertFile.add(this.textFieldInsertFile, 1, 0);

        Button buttonSelectFile = NodeFactory.buttonWithHandle("Select File", (event -> this.onClickInsertFileSearch()));
        gridPaneInsertFile.add(buttonSelectFile, 2, 0);


        GridPane gridPaneInsertConfig = NodeFactory.gridPaneWithProperties(Pos.CENTER, 10, 20);

        gridPaneInsertConfig.add(new Label("Entire File:"), 0, 0);
        gridPaneInsertConfig.add(this.checkBoxAllFile, 1, 0);

        gridPaneInsertConfig.add(new Label("From:"), 0, 1);
        gridPaneInsertConfig.add(this.textFieldInsertFromPage, 1, 1);

        gridPaneInsertConfig.add(new Label("To:"), 0, 2);
        gridPaneInsertConfig.add(this.textFieldInsertToPage, 1, 2);


        GridPane gridPaneIntoFile = NodeFactory.gridPaneWithProperties(Pos.CENTER, 10, 20);

        gridPaneIntoFile.add(new Label("Into File:"), 0, 0);
        gridPaneIntoFile.add(this.textFieldIntoFile, 1, 0);

        Button buttonSelectIntoFile = NodeFactory.buttonWithHandle("Select File", (event -> this.onClickIntoFileSearch()));
        gridPaneIntoFile.add(buttonSelectIntoFile, 2, 0);


        GridPane gridPaneIntoConfig = NodeFactory.gridPaneWithProperties(Pos.CENTER, 10, 20);

        gridPaneIntoConfig.add(new Label("After Page:"), 0, 0);
        gridPaneIntoConfig.add(this.textFieldIntoAfterPage, 1, 0);


        Button buttonExecute = NodeFactory.buttonWithHandle("Save", super.eventDo());
        buttonExecute.setDefaultButton(true);


        VBox vBox = new VBox(
                gridPaneInsertFile,
                gridPaneInsertConfig,
                gridPaneIntoFile,
                gridPaneIntoConfig,
                buttonExecute
        );

        vBox.setAlignment(Pos.CENTER);

        vBox.setSpacing(30);

        pane.getChildren().add(vBox);
    }
}