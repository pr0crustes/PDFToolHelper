package me.pr0crustes.frontend.gui.scene;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import me.pr0crustes.frontend.gui.classes.PassiveController;
import me.pr0crustes.frontend.gui.classes.internationalization.LocalizableStringGetter;
import me.pr0crustes.frontend.gui.classes.internationalization.LocalizableStrings;
import me.pr0crustes.frontend.gui.classes.internationalization.SupportedLanguages;
import me.pr0crustes.frontend.gui.classes.layout.NodeFactory;

/**
 * LanguageController will ask the user for a language.
 * Extends PassiveController.
 * @see PassiveController
 */
public class LanguageController extends PassiveController {

    /**
     * Just a constructor that calls super.
     * @param pane the Pane the GUI should be drawn on.
     */
    public LanguageController(Pane pane) {
        super(pane);
    }

    /**
     * Implementation of setupGUI.
     * @param pane the pane the GUI should be made on.
     * @see me.pr0crustes.frontend.gui.classes.Setup
     */
    @Override
    public void setupGUI(Pane pane) {

        GridPane gridPane = new GridPane();

        ComboBox<SupportedLanguages> comboBox = new ComboBox<>();

        SupportedLanguages[] allLanguages = SupportedLanguages.values();
        comboBox.getItems().addAll(allLanguages);
        comboBox.setValue(allLanguages[0]);

        gridPane.add(comboBox, 0, 0);

        Button button = NodeFactory.buttonWithHandle(
                LocalizableStrings.CONFIRM.localized(),
                (event -> {
                    LocalizableStringGetter.setLocale(comboBox.getValue());
                    new MenuController(pane);
                })
        );

        NodeFactory.maxButtonSize(button);
        gridPane.add(button, 0, 2);

        gridPane.setVgap(50);
        gridPane.setAlignment(Pos.CENTER);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(gridPane);

        NodeFactory.bindToParent(vBox, pane);
        pane.getChildren().add(vBox);

    }

}
