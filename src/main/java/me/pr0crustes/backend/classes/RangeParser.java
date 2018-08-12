package me.pr0crustes.backend.classes;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RangeParser {

    private final String rangeString;

    public RangeParser(String rangeString) {
        this.rangeString = rangeString;
    }

    public Integer[] getValues() {
        Set<Integer> parsedValues = new HashSet<>();

        String[] rangesParts = RangeParser.getMatches(rangeString, "\\d+_\\d+");
        String[] addParts = RangeParser.getMatches(rangeString, "[+]\\d+");
        String[] subParts = RangeParser.getMatches(rangeString, "[-]\\d+");

        for (String str : rangesParts) {
            String[] parts = str.split("_");
            for (int i = Integer.valueOf(parts[0]); i <= Integer.valueOf(parts[1]); i++) {
                parsedValues.add(i);
            }
        }

        for (String str : addParts) {
            parsedValues.add(RangeParser.stringAsUnsigned(str));
        }

        for (String str : subParts) {
            parsedValues.remove(RangeParser.stringAsUnsigned(str));
        }

        return parsedValues.toArray(new Integer[0]);
    }

    private static int stringAsUnsigned(String str) {
        return Integer.valueOf(str.replace("-", "").replace("+", ""));
    }

    private static String[] getMatches(String str, String regex) {
        List<String> matches = new ArrayList<>();
        Matcher match = Pattern.compile(regex).matcher(str);

        while (match.find()) {
            matches.add(match.group());
        }

        return matches.toArray(new String[0]);
    }

}
