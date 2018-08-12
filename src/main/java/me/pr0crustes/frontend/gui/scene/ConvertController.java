package me.pr0crustes.frontend.gui.scene;

import javafx.scene.layout.Pane;
import me.pr0crustes.backend.classes.FileSelector;
import me.pr0crustes.backend.classes.PDFConverter;
import me.pr0crustes.backend.classes.PDFManager;
import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import me.pr0crustes.frontend.gui.classes.ListController;
import me.pr0crustes.frontend.gui.classes.elements.FileListViewManagerFactory;
import me.pr0crustes.frontend.gui.classes.elements.ListViewManager;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
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
     * @throws NoFileException in case no file is selected.
     * @throws PermissionException in case of permission errors.
     * @throws ArgumentException in case of invalid arguments.
     * @see me.pr0crustes.frontend.gui.classes.ActionController
     */
    public void execute() throws NoFileException, PermissionException, ArgumentException {
        List<File> fileList = this.listViewManager.getList();

        if (fileList.size() == 0) {
            throw new ArgumentException();
        }

        File[] files = new File[fileList.size()];
        files = fileList.toArray(files);

        File saveAs = FileSelector.showSavePdfFile();

        PDFConverter converter = new PDFConverter(files);

        PDDocument document = converter.getDocumentFromImages();

        PDFManager.saveAs(document, saveAs);
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
