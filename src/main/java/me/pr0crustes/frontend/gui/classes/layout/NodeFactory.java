package me.pr0crustes.frontend.gui.classes.layout;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;


/**
 * NodeFactory is a class made of static methods to aggregate JavaFX node creation when repetitive.
 * Without it, code would be repeated all over the place when creating GUIs.
 */
public class NodeFactory {

    /**
     * Static method that sets a child width and height to its parent.
     * @param child the child.
     * @param parent the parent.
     */
    public static void bindToParent(Region child, Region parent) {
        child.prefWidthProperty().bind(parent.widthProperty());
        child.prefHeightProperty().bind(parent.heightProperty());
    }

    /**
     * Static method that creates a button and sets it handler in one line.
     * @param text the button text.
     * @param handler the button handler.
     * @return the Button as desired.
     */
    public static Button buttonWithHandle(String text, EventHandler<ActionEvent> handler) {
        Button button = new Button(text);
        button.setOnAction(handler);
        return button;
    }

    /**
     * Static method that receiving a button, setts it maxSize to the Double.MAX_VALUE,
     * making it as bigger as possible.
     * @param button the button.
     */
    public static void maxButtonSize(Button button) {
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    /**
     * Static method that creates a GridPane with the desired alignment, hgag and vgap.
     * This allow one line creation.
     * @param alignment the desired alignment.
     * @param hgap the desired grid hgap.
     * @param vgap the desired grid vgap.
     * @return a GripPane as desired.
     */
    public static GridPane gridPaneWithProperties(Pos alignment, double hgap, double vgap) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(alignment);
        gridPane.setHgap(hgap);
        gridPane.setVgap(vgap);
        return gridPane;
    }

    /**
     * Static method that creates a textField with a desired width and alignment.
     * @param width the desired width.
     * @param alignment the desired alignment.
     * @return a TextField as desired.
     */
    public static TextField textFieldWithWidthAlignment(double width, Pos alignment) {
        TextField textField = new TextField();
        textField.setPrefWidth(width);
        textField.setAlignment(alignment);
        return textField;
    }

    /**
     * Static method that creates a textField with a desired width, alignment and fontsize.
     * @param width the desired width.
     * @param alignment the desired alignment.
     * @param fontSize the desired font size.
     * @return a TextField as desired.
     */
    public static TextField textFieldWithWidthAlignmentFont(double width, Pos alignment, double fontSize) {
        TextField textField = NodeFactory.textFieldWithWidthAlignment(width, alignment);
        textField.setFont(Font.font(fontSize));
        return textField;
    }

}
