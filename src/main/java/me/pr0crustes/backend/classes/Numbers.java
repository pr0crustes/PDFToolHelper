package me.pr0crustes.backend.classes;

import javafx.scene.control.TextField;
import me.pr0crustes.backend.exeptions.ArgumentException;

public class Numbers {

    public static boolean isBetween(int number, int fromNumber, int toNumber) {
        return (number >= fromNumber && number <= toNumber);
    }

    public static int valueFromTextField(TextField textField) throws ArgumentException {
        try {
            return Integer.valueOf(textField.getText());
        } catch (NumberFormatException e) {
            throw new ArgumentException();
        }
    }

    public static int valueFromTextField(TextField textField, int errorCase) {
        try {
            return Numbers.valueFromTextField(textField);
        } catch (ArgumentException e) {
            return errorCase;
        }
    }
}
