package me.pr0crustes.backend.classes;

import javafx.scene.control.TextField;

public class Numbers {

    public static boolean isBetween(int number, int fromNumber, int toNumber) {
        return (number >= fromNumber && number <= toNumber);
    }

    public static int valueFromTextField(TextField textField) throws NumberFormatException {
        return Integer.valueOf(textField.getText());
    }

    public static int valueFromTextField(TextField textField, int errorCase) {
        try {
            return Numbers.valueFromTextField(textField);
        } catch (NumberFormatException e) {
            return errorCase;
        }
    }
}
