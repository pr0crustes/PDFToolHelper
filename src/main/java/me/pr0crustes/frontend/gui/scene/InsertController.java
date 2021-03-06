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
import me.pr0crustes.backend.classes.number.RangeEx;
import me.pr0crustes.backend.classes.pdf.PDFInserter;
import me.pr0crustes.backend.enums.FileExtensions;
import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NullFileException;
import me.pr0crustes.frontend.gui.classes.ActionController;
import me.pr0crustes.frontend.gui.classes.internationalization.LocalizableStrings;
import me.pr0crustes.frontend.gui.classes.layout.NodeFactory;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

/**
 * InsertController is the controller of Insert tab.
 * Handles inserting a pdf into other.
 * Extends ActionController.
 * @see ActionController
 */
public class InsertController extends ActionController {

    private File insertFile;
    private File intoFile;

    private TextField textFieldInsertFile;
    private TextField textFieldInsertRange;

    private TextField textFieldIntoFile;
    private TextField textFieldIntoAfterPage;

    /**
     * Just a constructor that calls super.
     * @param pane the Pane the GUI should be drawn on.
     */
    InsertController(Pane pane) {
        super(pane);
    }

    /**
     * Method that creates a PDFInserter, inserts a pdf into other and saves.
     * @throws ArgumentException in case of argument error.
     * @throws IOException in case of file error.
     * @throws NullFileException in case the file selected by the user to saveAs is null.
     * @see ActionController
     * @see PDFInserter
     */
    public void execute() throws ArgumentException, IOException, NullFileException {
        if (this.insertFile == null || this.intoFile == null) {
            throw new ArgumentException();
        }

        RangeEx rangeEx = new RangeEx(this.textFieldInsertRange.getText());

        int afterPage = Numbers.valueFromTextField(this.textFieldIntoAfterPage);

        PDFInserter pdfInserter = new PDFInserter(this.insertFile, this.intoFile);

        try (PDDocument document = pdfInserter.insertDocument(rangeEx, afterPage)) {
            new SaveFileSelector().savePDF(document);
        }
    }

    /**
     * Method that keep track of the insert file selected.
     */
    private void onClickInsertFileSearch() {
        this.insertFile = new SingleFileSelector().getSelection(FileExtensions.PDF);
        this.textFieldInsertFile.setText(FileSelector.getFilePath(this.insertFile));
    }

    /**
     * Method that keep track of the into file selected.
     */
    private void onClickIntoFileSearch() {
        this.intoFile = new SingleFileSelector().getSelection(FileExtensions.PDF);
        this.textFieldIntoFile.setText(FileSelector.getFilePath(this.intoFile));
    }

    /**
     * Implements setupGUI, creating the Insert Tab.
     * @param pane the pane the GUI should be made on.
     * @see me.pr0crustes.frontend.gui.classes.Setup
     */
    @Override
    public void setupGUI(Pane pane) {

        this.textFieldInsertFile = NodeFactory.textFieldWithWidthAlignmentFont(200, Pos.CENTER_LEFT, 10);
        this.textFieldInsertRange = NodeFactory.textFieldWithWidthAlignment(100, Pos.CENTER);
        this.textFieldIntoFile = NodeFactory.textFieldWithWidthAlignmentFont(200, Pos.CENTER_LEFT, 10);
        this.textFieldIntoAfterPage = NodeFactory.textFieldWithWidthAlignment(50, Pos.CENTER);


        GridPane gridPaneInsertFile = NodeFactory.gridPaneWithProperties(Pos.CENTER, 10, 20);

        gridPaneInsertFile.add(new Label(LocalizableStrings.FILE.localized()), 0, 0);
        gridPaneInsertFile.add(this.textFieldInsertFile, 1, 0);

        Button buttonSelectFile = NodeFactory.buttonWithHandle(LocalizableStrings.SELECT_FILE.localized(), (event -> this.onClickInsertFileSearch()));
        gridPaneInsertFile.add(buttonSelectFile, 2, 0);


        GridPane gridPaneInsertConfig = NodeFactory.gridPaneWithProperties(Pos.CENTER, 10, 20);

        gridPaneInsertConfig.add(new Label(LocalizableStrings.RANGE_EX.localized()), 0, 0);
        gridPaneInsertConfig.add(this.textFieldInsertRange, 1, 0);


        GridPane gridPaneIntoFile = NodeFactory.gridPaneWithProperties(Pos.CENTER, 10, 20);

        gridPaneIntoFile.add(new Label(LocalizableStrings.INTO_FILE.localized()), 0, 0);
        gridPaneIntoFile.add(this.textFieldIntoFile, 1, 0);

        Button buttonSelectIntoFile = NodeFactory.buttonWithHandle(LocalizableStrings.SELECT_FILE.localized(), (event -> this.onClickIntoFileSearch()));
        gridPaneIntoFile.add(buttonSelectIntoFile, 2, 0);


        GridPane gridPaneIntoConfig = NodeFactory.gridPaneWithProperties(Pos.CENTER, 10, 20);

        gridPaneIntoConfig.add(new Label(LocalizableStrings.AFTER_PAGE.localized()), 0, 0);
        gridPaneIntoConfig.add(this.textFieldIntoAfterPage, 1, 0);


        Button buttonExecute = NodeFactory.buttonWithHandle(LocalizableStrings.SAVE.localized(), super.eventDo());
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