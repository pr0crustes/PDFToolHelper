package me.pr0crustes.backend.classes.number;

import javafx.scene.control.TextField;
import me.pr0crustes.backend.exeptions.ArgumentException;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RangeEx {

    private final String rangeString;

    public RangeEx(String rangeString) {
        this.rangeString = rangeString;
    }

    public RangeEx(TextField textField) {
        this(textField.getText());
    }

    public Set<Integer> getValues() throws ArgumentException {
        Set<Integer> parsedValues = new HashSet<>();

        Set<String> rangesParts = RangeEx.getMatches(rangeString, "\\d+_\\d+");
        for (String str : rangesParts) {
            String[] parts = str.split("_");
            for (int i = Integer.valueOf(parts[0]); i <= Integer.valueOf(parts[1]); i++) {
                parsedValues.add(i);
            }
        }

        Set<String> addParts = RangeEx.getMatches(rangeString, "[+]\\d+");
        for (String str : addParts) {
            parsedValues.add(RangeEx.stringAsUnsigned(str));
        }

        Set<String> subParts = RangeEx.getMatches(rangeString, "[-]\\d+");
        for (String str : subParts) {
            parsedValues.remove(RangeEx.stringAsUnsigned(str));
        }

        return parsedValues;
    }

    private static int stringAsUnsigned(String str) throws ArgumentException {
        try {
            return Integer.valueOf(str.replace("-", "").replace("+", ""));
        } catch (NumberFormatException e) {
            throw new ArgumentException();
        }
    }

    private static Set<String> getMatches(String str, String regex) {
        Set<String> matches = new HashSet<>();
        Matcher match = Pattern.compile(regex).matcher(str);

        while (match.find()) {
            matches.add(match.group());
        }

        return matches;
    }

}
