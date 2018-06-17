package me.pr0crustes.frontend.gui.scene.merge;

import javafx.scene.layout.Pane;
import me.pr0crustes.backend.classes.FileExtensions;
import me.pr0crustes.backend.classes.FileSelector;
import me.pr0crustes.backend.classes.PDFMerger;
import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import me.pr0crustes.frontend.gui.classes.ListController;
import me.pr0crustes.frontend.gui.classes.elements.FileListViewManagerFactory;
import me.pr0crustes.frontend.gui.classes.elements.ListViewManager;

import java.io.File;
import java.util.List;

public class MergeController extends ListController {

    private ListViewManager<File> listViewManager;

    public MergeController(Pane pane) {
        super(pane);
    }

    @Override
    public File addNewFileToList() {
        return FileSelector.askForSelect(FileExtensions.PDF);
    }

    @Override
    public void execute() throws NoFileException, PermissionException, ArgumentException {

        List<File> fileList = this.listViewManager.getList();

        if (fileList.size() == 0) {
            throw new ArgumentException();
        }

        File[] filesToMerge = new File[fileList.size()];
        filesToMerge = fileList.toArray(filesToMerge);

        PDFMerger merger = new PDFMerger(filesToMerge);

        File saveDestiny = FileSelector.showSavePdfFile();

        merger.mergeFiles(saveDestiny);
    }

    @Override
    public void setupGUI(Pane pane) {
       this.listViewManager = new FileListViewManagerFactory(this).setupListView(pane);
    }
}