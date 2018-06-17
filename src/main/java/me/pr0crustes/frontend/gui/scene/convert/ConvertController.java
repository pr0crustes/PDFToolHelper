package me.pr0crustes.frontend.gui.scene.convert;

import javafx.scene.layout.Pane;
import me.pr0crustes.backend.classes.FileSelector;
import me.pr0crustes.backend.classes.PDFConverter;
import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import me.pr0crustes.frontend.gui.classes.ListController;
import me.pr0crustes.frontend.gui.classes.elements.FileListViewManagerFactory;
import me.pr0crustes.frontend.gui.classes.elements.ListViewManager;

import java.io.File;
import java.util.List;

public class ConvertController extends ListController {

    private ListViewManager<File> listViewManager;

    public ConvertController(Pane pane) {
        super(pane);
    }

    @Override
    public File addNewFileToList() {
        return FileSelector.askForSelect();
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

    @Override
    public void setupGUI(Pane pane) {

        this.listViewManager = new FileListViewManagerFactory(this).setupListView(pane);
    }
}
