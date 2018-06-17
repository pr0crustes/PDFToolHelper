package me.pr0crustes.frontend.gui.classes.layout;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;


public class NodeFactory {

    public static void bindToParent(Region child, Region parent) {
        child.prefWidthProperty().bind(parent.widthProperty());
        child.prefHeightProperty().bind(parent.heightProperty());
    }

    public static Button buttonWithHandle(String text, EventHandler<ActionEvent> handler) {
        Button button = new Button(text);
        button.setOnAction(handler);
        return button;
    }

    public static void maxButtonSize(Button button) {
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    public static GridPane gridPaneWithProperties(Pos alignment, double hgap, double vgap) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(alignment);
        gridPane.setHgap(hgap);
        gridPane.setVgap(vgap);
        return gridPane;
    }

}
