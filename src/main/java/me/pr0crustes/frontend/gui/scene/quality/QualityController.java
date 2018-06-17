package me.pr0crustes.frontend.gui.scene.quality;

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
import me.pr0crustes.backend.classes.PDFQualityModifier;
import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import me.pr0crustes.frontend.gui.classes.ActionController;
import me.pr0crustes.frontend.gui.classes.layout.NodeFactory;

import java.io.File;

public class QualityController extends ActionController {

    private TextField textFieldFile;

    private TextField textFieldDpi;

    private File selectedFile;

    public QualityController(Pane pane) {
        super(pane);
    }

    private void onClickSearch() {
        this.selectedFile = FileSelector.askForSelect(FileExtensions.PDF);

        String filePath = FileSelector.getFilePath(this.selectedFile);

        this.textFieldFile.setText(filePath);
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
            return Integer.valueOf(this.textFieldDpi.getText());
        } catch (NumberFormatException e) {
            throw new ArgumentException();
        }
    }

    @Override
    public void setupGUI(Pane pane) {
        
        this.textFieldFile = new TextField();
        this.textFieldFile.setPrefWidth(300);
        this.textFieldFile.setFont(Font.font(10));

        this.textFieldDpi = new TextField();
        this.textFieldDpi.setPrefWidth(50);
        this.textFieldDpi.setAlignment(Pos.CENTER);

        GridPane gridPaneFile = NodeFactory.gridPaneWithProperties(Pos.CENTER, 10, 20);

        gridPaneFile.add(new Label("File:"), 0, 0);
        gridPaneFile.add(this.textFieldFile, 1, 0);

        Button buttonSelectFile = NodeFactory.buttonWithHandle("Select File", (event -> this.onClickSearch()));
        gridPaneFile.add(buttonSelectFile, 2, 0);

        GridPane gridPaneCrop = NodeFactory.gridPaneWithProperties(Pos.CENTER, 10, 20);

        gridPaneCrop.add(new Label("DPI:"), 0, 0);
        gridPaneCrop.add(this.textFieldDpi, 1, 0);

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
