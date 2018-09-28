package me.pr0crustes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import me.pr0crustes.frontend.gui.classes.LocalizableStrings;
import me.pr0crustes.frontend.gui.scene.MenuController;

/**
 * Class that is the entry point of the program.
 * Since it is a JavaFX app, extends Application.
 * @see Application
 */
public class Starter extends Application {

    /**
     * A Stage singleton. This will come in hand when we need to show file select / file save dialogs.
     */
    public static Stage mainStage;

    /**
     * JavaFX Application start method.
     * Launchs the main window.
     * Creates a MenuController.
     * @param primaryStage the JavaFX primaryStage.
     * @see Application
     * @see MenuController
     */
    @Override
    public void start(Stage primaryStage) {
        Starter.mainStage = primaryStage;

        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle(LocalizableStrings.TITLE.localized());
        primaryStage.setResizable(false);
        primaryStage.show();

        new MenuController(root);
    }

    /**
     * Main, entry point!
     * @param args something...
     */
    public static void main(String[] args) {
        // Keep this line. Works better with it. see https://pdfbox.apache.org/2.0/getting-started.html
        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
        launch(args);
    }
}
