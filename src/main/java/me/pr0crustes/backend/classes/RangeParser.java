package me.pr0crustes.backend.classes;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RangeParser {

    private final String rangeString;

    public RangeParser(String rangeString) {
        this.rangeString = rangeString;
    }

    public Set<Integer> getValues() {
        Set<Integer> parsedValues = new HashSet<>();

        Set<String> rangesParts = RangeParser.getMatches(rangeString, "\\d+_\\d+");
        for (String str : rangesParts) {
            String[] parts = str.split("_");
            for (int i = Integer.valueOf(parts[0]); i <= Integer.valueOf(parts[1]); i++) {
                parsedValues.add(i);
            }
        }

        Set<String> addParts = RangeParser.getMatches(rangeString, "[+]\\d+");
        for (String str : addParts) {
            parsedValues.add(RangeParser.stringAsUnsigned(str));
        }

        Set<String> subParts = RangeParser.getMatches(rangeString, "[-]\\d+");
        for (String str : subParts) {
            parsedValues.remove(RangeParser.stringAsUnsigned(str));
        }

        return parsedValues;
    }

    private static int stringAsUnsigned(String str) {
        return Integer.valueOf(str.replace("-", "").replace("+", ""));
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
