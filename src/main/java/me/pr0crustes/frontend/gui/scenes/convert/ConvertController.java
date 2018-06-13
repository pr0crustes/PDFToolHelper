package me.pr0crustes.frontend.gui.scenes.convert;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import me.pr0crustes.backend.classes.FileSelector;
import me.pr0crustes.backend.classes.PDFConverter;
import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import me.pr0crustes.frontend.gui.classes.ActionController;
import me.pr0crustes.frontend.gui.classes.elements.ListViewManager;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ConvertController extends ActionController {

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
        File inputFile = FileSelector.askForSelect();
        if (inputFile != null) {
            this.listViewManager.addObject(inputFile);
        }
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

    public void execute() throws NoFileException, PermissionException, ArgumentException {

        List<File> fileList = this.listViewManager.getList();

        if (fileList.size() == 0) {
            throw new ArgumentException();
        }


        File[] files = new File[fileList.size()];
        files = fileList.toArray(files);

        File saveDestiny = FileSelector.showSavePdfFile();

        PDFConverter converter = new PDFConverter(files);

        converter.convertToPDF(saveDestiny);
    }
}
