package me.pr0crustes.frontend.gui.classes;

import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * AlertFactory is a class that aggregates Alert related stuff.
 */
class AlertFactory {

    /**
     * Enum with pre-made alerts.
     * Each element is self explanatory.
     */
    public enum DefinedAlert {

        fileError(
                Alert.AlertType.WARNING,
                LocalizableStrings.FILE_ERROR.localized(),
                LocalizableStrings.AN_ERROR_OCCURRED_WITH_THE_FILE.localized(),
                LocalizableStrings.CHECK_YOUR_INPUT.localized()),

        invalidArgument(
                Alert.AlertType.WARNING,
                LocalizableStrings.ARGUMENT_ERROR.localized(),
                LocalizableStrings.INVALID_ARGUMENTS.localized(),
                LocalizableStrings.AT_LEAST_ONE_ARGUMENT_IS_INVALID.localized()),

        unknownError(
                Alert.AlertType.ERROR,
                LocalizableStrings.UNKNOWN_ERROR.localized(),
                LocalizableStrings.AN_UNKNOWN_ERROR_OCCURRED.localized(),
                LocalizableStrings.REPORT_IF_YOU_THINK_THIS_IS_A_BUG.localized());



        private final Alert.AlertType type;
        private final String title;
        private final String header;
        private final String text;

        /**
         * Alert constructor.
         * @param type the alert type.
         * @param title the alert title.
         * @param header the alert header.
         * @param text the alert text.
         */
        DefinedAlert(Alert.AlertType type, String title, String header, String text) {
            this.type = type;
            this.title = title;
            this.header = header;
            this.text = text;
        }

        /**
         * Method that sends the alert.
         */
        public void sendAlert() {
            AlertFactory.sendNewAlert(this.type, this.title, this.header, this.text);
        }

    }

    /**
     * Static method that shows an alert in the JavaFX thread.
     * @param type the alert type.
     * @param title the alert title.
     * @param header the alert header.
     * @param text the alert text.
     */
    private static void sendNewAlert(Alert.AlertType type, String title, String header, String text) {
        Platform.runLater(() -> AlertFactory.createAlert(type, title, header, text).showAndWait());
    }

    /**
     * Static method that creates an alert and returns it.
     * Just a wrapper to do things in one line.
     * @param type the alert type.
     * @param title the alert title.
     * @param header the alert header.
     * @param text the alert text.
     * @return an Alert as desired.
     */
    private static Alert createAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);

        return alert;
    }
}
