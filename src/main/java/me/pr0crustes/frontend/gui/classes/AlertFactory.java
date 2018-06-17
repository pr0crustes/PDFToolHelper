package me.pr0crustes.frontend.gui.classes;

import javafx.application.Platform;
import javafx.scene.control.Alert;

class AlertFactory {

    public enum DefinedAlert {

        noFile(
                Alert.AlertType.WARNING,
                "File Error",
                "No Select File",
                "In order to the performed action work, a file must be selected."),

        invalidArgument(
                Alert.AlertType.WARNING,
                "Argument Error",
                "Invalid Arguments",
                "At least one argument passed is not valid."),

        errorAtSave(
                Alert.AlertType.WARNING,
                "Argument Error",
                "Invalid Arguments",
                "At least one argument passed is not valid."),

        unknownError(
                Alert.AlertType.ERROR,
                "Unknown Error",
                "An Unknown Error Occurred",
                "Report to the devs if you think this is a consistent bug.");



        private final Alert.AlertType type;
        private final String title;
        private final String header;
        private final String text;

        DefinedAlert(Alert.AlertType type, String title, String header, String text) {
            this.type = type;
            this.title = title;
            this.header = header;
            this.text = text;
        }

        public void sendAlert() {
            AlertFactory.sendNewAlert(this.type, this.title, this.header, this.text);
        }
    }

    private static void sendNewAlert(Alert.AlertType type,
                                     String title,
                                     String header,
                                     String text) {

        Platform.runLater(() -> AlertFactory.createAlert(type, title, header, text).showAndWait());
    }

    private static Alert createAlert(Alert.AlertType type,
                                    String title,
                                    String header,
                                    String text) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);

        return alert;
    }
}
