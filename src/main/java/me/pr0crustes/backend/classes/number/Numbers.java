package me.pr0crustes.backend.classes.number;

import javafx.scene.control.TextField;
import me.pr0crustes.backend.exeptions.ArgumentException;

/**
 * Class made of static methods that are in some way relative with numbers.
 */
public class Numbers {

    /**
     * Method that converts the content of a TextField into a number, throwing ArgumentException if not valid.
     * @param textField the TextField.
     * @return the int value.
     * @throws ArgumentException if the textfield content is not a valid number.
     */
    public static int valueFromTextField(TextField textField) throws ArgumentException {
        try {
            return Integer.valueOf(textField.getText());
        } catch (NumberFormatException e) {
            throw new ArgumentException();
        }
    }

}
