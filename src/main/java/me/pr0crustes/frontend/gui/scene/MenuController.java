package me.pr0crustes.frontend.gui.scene;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import me.pr0crustes.frontend.gui.classes.PassiveController;
import me.pr0crustes.frontend.gui.classes.internationalization.LocalizableStrings;
import me.pr0crustes.frontend.gui.classes.layout.NodeFactory;

/**
 * MenuController is the root controller.
 * Creates all the buttons for others tabs.
 * Extends PassiveController.
 * @see PassiveController
 */
public class MenuController extends PassiveController {

    /**
     * Just a constructor that calls super.
     * @param pane the Pane the GUI should be drawn on.
     */
    public MenuController(Pane pane) {
        super(pane);
    }

    /**
     * Implementation of setupGUI.
     * @param pane the pane the GUI should be made on.
     * @see me.pr0crustes.frontend.gui.classes.Setup
     */
    @Override
    public void setupGUI(Pane pane) {
        NodeFactory.updateStageSize(600, 400, true);
        SplitPane splitPane = this.createSplitPane();
        NodeFactory.bindToParent(splitPane, pane);
        pane.getChildren().add(splitPane);
    }

    /**
     * Method that creates the GUI splitPane and returns.
     * @return a SplitPane with everything on it.
     */
    private SplitPane createSplitPane() {
        SplitPane splitPane = new SplitPane();

        StackPane stackPaneRight = new StackPane();
        StackPane stackPaneLeft = this.createLeftStackPane(stackPaneRight);

        splitPane.getItems().addAll(stackPaneLeft, stackPaneRight);
        splitPane.setDividerPositions(0.25);

        return splitPane;
    }

    /**
     * Method that creates a StackPane with all the buttons.
     * @param stackPaneRight the right stackPane. This is needed because it will be passed to all the others controllers.
     * @return the stackPane that will be on the left.
     */
    private StackPane createLeftStackPane(StackPane stackPaneRight) {

        Button[] buttons = {
                NodeFactory.buttonWithHandle(LocalizableStrings.CROP.localized(), (event -> new CropController(stackPaneRight))),
                NodeFactory.buttonWithHandle(LocalizableStrings.MERGE.localized(), (event -> new MergeController(stackPaneRight))),
                NodeFactory.buttonWithHandle(LocalizableStrings.INSERT.localized(), (event -> new InsertController(stackPaneRight))),
                NodeFactory.buttonWithHandle(LocalizableStrings.CONVERT.localized(), (event -> new ConvertController(stackPaneRight))),
                NodeFactory.buttonWithHandle(LocalizableStrings.QUALITY.localized(), (event -> new QualityController(stackPaneRight)))
        };

        GridPane gridPane = new GridPane();

        for (int i = 0 ; i < buttons.length ; i++) {
            NodeFactory.maxButtonSize(buttons[i]);
            gridPane.add(buttons[i], 0, i);
        }

        gridPane.setVgap(30);
        gridPane.setAlignment(Pos.CENTER);

        return new StackPane(
            gridPane
        );
    }

}
