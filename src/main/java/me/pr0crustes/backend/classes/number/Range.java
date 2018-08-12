package me.pr0crustes.backend.classes.number;

import me.pr0crustes.backend.exeptions.ArgumentException;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Range {

    private final String rangeString;

    public Range(String rangeString) {
        this.rangeString = rangeString;
    }

    public Set<Integer> getValues() throws ArgumentException{
        Set<Integer> parsedValues = new HashSet<>();

        Set<String> rangesParts = Range.getMatches(rangeString, "\\d+_\\d+");
        for (String str : rangesParts) {
            String[] parts = str.split("_");
            for (int i = Integer.valueOf(parts[0]); i <= Integer.valueOf(parts[1]); i++) {
                parsedValues.add(i);
            }
        }

        Set<String> addParts = Range.getMatches(rangeString, "[+]\\d+");
        for (String str : addParts) {
            parsedValues.add(Range.stringAsUnsigned(str));
        }

        Set<String> subParts = Range.getMatches(rangeString, "[-]\\d+");
        for (String str : subParts) {
            parsedValues.remove(Range.stringAsUnsigned(str));
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
