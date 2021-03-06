package me.pr0crustes.frontend.gui.scene;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import me.pr0crustes.backend.classes.file.FileSelector;
import me.pr0crustes.backend.classes.file.SaveFileSelector;
import me.pr0crustes.backend.classes.file.SingleFileSelector;
import me.pr0crustes.backend.classes.number.Numbers;
import me.pr0crustes.backend.classes.pdf.PDFQualityModifier;
import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NullFileException;
import me.pr0crustes.frontend.gui.classes.ActionController;
import me.pr0crustes.frontend.gui.classes.internationalization.LocalizableStrings;
import me.pr0crustes.frontend.gui.classes.layout.NodeFactory;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

/**
 * QualityController is the controller of Quality tab.
 * Handles changing the dpi of a pdf.
 * Extends ActionController.
 * @see ActionController
 */
public class QualityController extends ActionController {

    private File selectedFile;

    private TextField textFieldFile;
    private TextField textFieldDpi;

    /**
     * Just a constructor that calls super.
     * @param pane the Pane that the GUI should be drawn on.
     * @see ActionController
     */
    QualityController(Pane pane) {
        super(pane);
    }

    /**
     * Method that setups selectedFile and textFieldFile with user input.
     */
    private void onClickSearch() {
        this.selectedFile = new SingleFileSelector().getSelection();
        this.textFieldFile.setText(FileSelector.getFilePath(this.selectedFile));
    }

    /**
     * Method that creates a PDFQualityModifier, changes the pdf dpi and saves.
     * @throws ArgumentException in case of invalid args.
     * @throws IOException in case of file related error.
     * @throws NullFileException in case the file selected by the user to saveAs is null.
     * @see ActionController
     * @see PDFQualityModifier
     */
    @Override
    public void execute() throws ArgumentException, IOException, NullFileException {
        if (this.selectedFile == null) {
            throw new ArgumentException();
        }

        int dpi = Numbers.valueFromTextField(this.textFieldDpi);
        PDFQualityModifier qualityModifier = new PDFQualityModifier(this.selectedFile);

        try (PDDocument document = qualityModifier.getDocumentWithDPI(dpi)) {
            new SaveFileSelector().savePDF(document);
        }
    }

    /**
     * Implements setupGUI, creating the Quality Tab.
     * @param pane the pane the GUI should be made on.
     * @see me.pr0crustes.frontend.gui.classes.Setup
     */
    @Override
    public void setupGUI(Pane pane) {

        this.textFieldFile = NodeFactory.textFieldWithWidthAlignmentFont(200, Pos.CENTER_LEFT, 10);
        this.textFieldDpi = NodeFactory.textFieldWithWidthAlignment(50, Pos.CENTER);

        GridPane gridPaneFile = NodeFactory.gridPaneWithProperties(Pos.CENTER, 10, 20);

        gridPaneFile.add(new Label(LocalizableStrings.FILE.localized()), 0, 0);
        gridPaneFile.add(this.textFieldFile, 1, 0);

        Button buttonSelectFile = NodeFactory.buttonWithHandle(LocalizableStrings.SELECT_FILE.localized(), (event -> this.onClickSearch()));
        gridPaneFile.add(buttonSelectFile, 2, 0);

        GridPane gridPaneCrop = NodeFactory.gridPaneWithProperties(Pos.CENTER, 10, 20);

        gridPaneCrop.add(new Label(LocalizableStrings.DPI.localized()), 0, 0);
        gridPaneCrop.add(this.textFieldDpi, 1, 0);

        Button buttonExecute = NodeFactory.buttonWithHandle(LocalizableStrings.SAVE.localized(), super.eventDo());
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
