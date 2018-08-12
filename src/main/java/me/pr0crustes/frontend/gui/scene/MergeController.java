package me.pr0crustes.frontend.gui.scene;

import javafx.scene.layout.Pane;
import me.pr0crustes.backend.classes.file.FileSelector;
import me.pr0crustes.backend.classes.pdf.PDFManager;
import me.pr0crustes.backend.classes.pdf.PDFMerger;
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
 * MergeController is the controller of Merge tab.
 * Creates and handles the GUI that merges PDFs.
 * Extends ListController.
 * @see ListController
 * @see ListViewManager
 */
public class MergeController extends ListController {

    private ListViewManager<File> listViewManager;

    /**
     * Just a constructor that calls super.
     * @param pane the Pane where the GUI will be drawn on.
     * @see ListController
     */
    MergeController(Pane pane) {
        super(pane);
    }

    /**
     * Implementation of execute, merges the files and saves.
     * @throws NoFileException in case no file is selected.
     * @throws PermissionException in case of permission errors.
     * @throws ArgumentException in case of invalid arguments.
     * @see me.pr0crustes.frontend.gui.classes.ActionController
     */
    @Override
    public void execute() throws NoFileException, PermissionException, ArgumentException {

        List<File> fileList = this.listViewManager.getList();

        if (fileList.size() == 0) {
            throw new ArgumentException();
        }

        File[] filesToMerge = new File[fileList.size()];
        filesToMerge = fileList.toArray(filesToMerge);

        File saveAs = FileSelector.showSavePdfFile();

        PDFMerger merger = new PDFMerger(filesToMerge);

        PDDocument document = merger.mergeFiles();

        PDFManager.saveAs(document, saveAs);
    }

    /**
     * Implements setupGUI, creating the Merge Tab.
     * @param pane the pane the GUI should be made on.
     * @see me.pr0crustes.frontend.gui.classes.Setup
     */
    @Override
    public void setupGUI(Pane pane) {
       this.listViewManager = new FileListViewManagerFactory(this).setupListView(pane);
    }
}