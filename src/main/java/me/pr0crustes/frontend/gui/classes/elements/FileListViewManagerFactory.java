package me.pr0crustes.frontend.gui.classes.elements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import me.pr0crustes.frontend.gui.classes.ListController;
import me.pr0crustes.frontend.gui.classes.internationalization.LocalizableStrings;
import me.pr0crustes.frontend.gui.classes.layout.NodeFactory;

import java.io.File;

/**
 * FileListViewManagerFactory is a class that facilitates the creation and use of ListViewManager,
 * automatically creating the main tableView GUI.
 */
public class FileListViewManagerFactory {

    private final ListController controller;

    /**
     * Constructor that receives a ListController.
     * @param controller thw ListController the FileListViewManagerFactory will help.
     */
    public FileListViewManagerFactory(ListController controller) {
        this.controller = controller;
    }

    /**
     * Method that creates the GUI and returns a ListViewManager.
     * @param pane the Pane that the GUI will be drawn on.
     * @return a ListViewManager with type File
     */
    public ListViewManager<File> setupListView(Pane pane) {

        ListView<File> listViewFiles = new ListView<>();

        listViewFiles.prefHeight(50);
        listViewFiles.prefWidth(50);

        ListViewManager<File> listViewManager = new ListViewManager<>(listViewFiles);

        Button[] buttons = {
                NodeFactory.buttonWithHandle("+", (event -> listViewManager.addObjects(this.controller.addNewFilesToList()))),
                NodeFactory.buttonWithHandle("-", (event -> listViewManager.removeSelected())),
                NodeFactory.buttonWithHandle("↑", (event -> listViewManager.moveSelectedUp())),
                NodeFactory.buttonWithHandle("↓", (event -> listViewManager.moveSelectedDown()))
        };

        GridPane gridPaneButton = new GridPane();

        for (int i = 0; i < buttons.length; i++) {
            gridPaneButton.add(buttons[i], i, 0);
        }

        Button buttonSave = NodeFactory.buttonWithHandle(LocalizableStrings.SAVE.localized(), this.controller.eventDo());
        buttonSave.setDefaultButton(true);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(listViewFiles, gridPaneButton, buttonSave);

        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(50, 100, 50, 100));

        NodeFactory.bindToParent(vBox, pane);

        pane.getChildren().add(vBox);

        return listViewManager;
    }

}
