package me.pr0crustes.frontend.gui.scene.menu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import me.pr0crustes.frontend.gui.classes.PassiveController;
import me.pr0crustes.frontend.gui.classes.layout.NodesHelper;
import me.pr0crustes.frontend.gui.scene.convert.ConvertController;
import me.pr0crustes.frontend.gui.scene.crop.CropController;
import me.pr0crustes.frontend.gui.scene.merge.MergeController;
import me.pr0crustes.frontend.gui.scene.quality.QualityController;

public class MenuController extends PassiveController {

    public MenuController(Pane pane) {
        super(pane);
    }

    @Override
    public void setupGUI(Pane pane) {
        SplitPane splitPane = this.createSplitPane();

        NodesHelper.bindToParent(splitPane, pane);

        pane.getChildren().add(splitPane);
    }

    private SplitPane createSplitPane() {

        SplitPane splitPane = new SplitPane();

        StackPane stackPaneRight = new StackPane();
        StackPane stackPaneLeft = this.createLeftStackPane(stackPaneRight);

        splitPane.getItems().addAll(stackPaneLeft, stackPaneRight);
        splitPane.setDividerPositions(0.25);

        return splitPane;
    }

    private StackPane createLeftStackPane(StackPane stackPaneRight) {

        Button[] buttons = {
                NodesHelper.buttonWithHandle("Crop", (event -> new CropController(stackPaneRight))),
                NodesHelper.buttonWithHandle("Merge", (event -> new MergeController(stackPaneRight))),
                NodesHelper.buttonWithHandle("Convert", (event -> new ConvertController(stackPaneRight))),
                NodesHelper.buttonWithHandle("Quality", (event -> new QualityController(stackPaneRight)))
        };

        GridPane gridPane = new GridPane();

        for (int i = 0 ; i < buttons.length ; i++) {
            NodesHelper.maxButtonSize(buttons[i]);
            gridPane.add(buttons[i], 0, i);
        }

        gridPane.setVgap(30);
        gridPane.setAlignment(Pos.CENTER);

        return new StackPane(
            gridPane
        );
    }

}
