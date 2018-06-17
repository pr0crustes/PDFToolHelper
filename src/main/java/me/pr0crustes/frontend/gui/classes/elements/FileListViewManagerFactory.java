package me.pr0crustes.frontend.gui.classes.elements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import me.pr0crustes.frontend.gui.classes.ListController;
import me.pr0crustes.frontend.gui.classes.layout.NodeFactory;

import java.io.File;


public class FileListViewManagerFactory {

    private final ListController controller;

    public FileListViewManagerFactory(ListController controller) {
        this.controller = controller;
    }

    public ListViewManager<File> setupListView(Pane pane) {

        ListView<File> listViewFiles = new ListView<>();

        listViewFiles.prefHeight(50);
        listViewFiles.prefWidth(50);

        ListViewManager<File> listViewManager = new ListViewManager<>(listViewFiles);

        Button[] buttons = {
                NodeFactory.buttonWithHandle("+", (event -> listViewManager.addObject(this.controller.addNewFileToList()))),
                NodeFactory.buttonWithHandle("-", (event -> listViewManager.removeSelected())),
                NodeFactory.buttonWithHandle("↑", (event -> listViewManager.moveSelectedUp())),
                NodeFactory.buttonWithHandle("↓", (event -> listViewManager.moveSelectedDown()))
        };

        GridPane gridPaneButton = new GridPane();

        for (int i = 0; i < buttons.length; i++) {
            gridPaneButton.add(buttons[i], i, 0);
        }

        Button buttonSave = NodeFactory.buttonWithHandle("Save", this.controller.eventDo());

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