package me.pr0crustes.frontend.gui.scene;

import javafx.scene.layout.Pane;
import me.pr0crustes.backend.classes.file.SaveFileSelector;
import me.pr0crustes.backend.classes.pdf.PDFConverter;
import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.frontend.gui.classes.ListController;
import me.pr0crustes.frontend.gui.classes.elements.FileListViewManagerFactory;
import me.pr0crustes.frontend.gui.classes.elements.ListViewManager;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * ConvertController is the controller of Convert tab.
 * Creates and handles the GUI that converts images into PDF.
 * Extends ListController.
 * @see ListController
 * @see ListViewManager
 */
public class ConvertController extends ListController {

    private ListViewManager<File> listViewManager;

    /**
     * Just a constructor that calls super.
     * @param pane the Pane where the GUI will be drawn on.
     * @see ListController
     */
    ConvertController(Pane pane) {
        super(pane);
    }

    /**
     * Implementation of execute, converts the files and saves.
     * @throws IOException in case of file error.
     * @throws ArgumentException in case of invalid arguments.
     * @see me.pr0crustes.frontend.gui.classes.ActionController
     */
    public void execute() throws IOException, ArgumentException {
        List<File> fileList = this.listViewManager.getList();

        if (fileList.size() == 0) {
            throw new ArgumentException();
        }

        File saveAs = new SaveFileSelector().getSelection();

        PDFConverter converter = new PDFConverter(fileList);

        PDDocument document = converter.getDocumentFromImages();
        document.save(saveAs);
    }

    /**
     * Implements setupGUI, creating the Convert Tab.
     * @param pane the pane the GUI should be made on.
     * @see me.pr0crustes.frontend.gui.classes.Setup
     */
    @Override
    public void setupGUI(Pane pane) {
        this.listViewManager = new FileListViewManagerFactory(this).setupListView(pane);
    }

}
