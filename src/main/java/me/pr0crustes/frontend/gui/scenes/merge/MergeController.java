package me.pr0crustes.frontend.gui.scenes.merge;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import me.pr0crustes.backend.classes.FileExtensions;
import me.pr0crustes.backend.classes.FileSelector;
import me.pr0crustes.backend.classes.PDFMerger;
import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import me.pr0crustes.frontend.gui.classes.ActionController;
import me.pr0crustes.frontend.gui.classes.elements.ListViewManager;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MergeController extends ActionController {

    @FXML
    private ListView<File> listViewFiles;

    private ListViewManager<File> listViewManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.listViewManager = new ListViewManager<>(this.listViewFiles);
        super.initialize(location, resources);
    }

    @FXML
    void onClickPlus() {
        File inputFile = FileSelector.askForSelect(FileExtensions.PDF);
        this.listViewManager.addObject(inputFile);
    }

    @FXML
    void onClickMinus() {
        this.listViewManager.removeSelected();
    }

    @FXML
    void onClickUp() {
        this.listViewManager.moveSelectedUp();
    }

    @FXML
    void onClickDown() {
        this.listViewManager.moveSelectedDown();
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

}