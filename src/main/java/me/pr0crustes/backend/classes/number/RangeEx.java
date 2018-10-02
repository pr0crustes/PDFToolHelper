package me.pr0crustes.backend.classes.number;

import me.pr0crustes.backend.exeptions.ArgumentException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * From README:
 * 
 * What is RangeEx?
 * A few TextFields around the program expects a valid RangeEx as input, but what is it?  
 *
 * RangeEx, short of Range Expression, is a simple expression system, by me designed, 
 * that represents a finite amount of numbers.  
 *
 *  RangeEx has 3 basics operations that can be concatenated:  
 *
 * UNDERSCORE ('_')  
 * An underscore represents an interval of numbers, going from the number on the left to the number on the right of the underscore.  
 * Generalizing, an input "X_Y", with X and Y being positive integers and X < Y, will result in all the numbers from X to Y, including Y.  
 * Should always be used between two numbers.  
 *
 * Examples:  
 * "1_4" will result in [1, 2, 3, 4].  
 * "10_15" will result in [10, 11, 12, 13, 14, 15].  
 * "_6" or "3_" will result in an exception.  
 *
 * PLUS ('+')  
 * A plus represents an addition of the number that follows it.  
 * Generalizing, an input "+X", with X being a positive integer, will result in an interval with only the value X.  
 * Every plus signal should be followed by a valid number.  
 *
 * Examples:  
 * "+1" will result in [1].  
 * "+7" will result in [7].  
 * "+" or "++0" will result in an exception.  
 *
 * MINUS ('-')  
 * A minus represents a subtraction of the number that follows it from a interval.  
 * Generalizing, an input "-X", with X being a positive integer, will result in the current input interval but without X.  
 * Every minus signal should be followed by a valid number.
 *
 * Example:  
 * "1_4-3" will result in [1, 2, 4].  
 * "5_7-6" will result in [5, 7].  
 *
 * More on Concatenation 
 * One or more spaces (" ") are a valid input to separate things.  
 * This way, "1_3 6_8" will result in [1, 2, 3, 6, 7, 8].  
 * The same result can also be achieved with "1_3+6_8".  
 *
 * Keeping in mind the order of the operations is important:  
 * Every range (underscore) is evaluated first;  
 * Then every addition (plus) is added to the previous interval;  
 * Then every subtraction (minus) is removed from the interval. 
 *
 * Because of this order, are valid unintuitive RangeEx:  
 * "+13 +10", the same as "+13+10", resulting in [10, 13].  
 * "-2 1_3", the same as "-2+1_3", resulting in [1, 3].  
 * "-5 +5 +5 +3", the same as "-5+5+5+3", resulting in [3], since subtraction is the last thing that happens.  
 * "3_7 -4 +20 25_28 +50", the same as "3_7-4+20+25_28+50", resulting in [3, 5, 6, 7, 20, 25, 26, 27, 28, 50].
 *
 * '*' can be used to match every number. In this case, all other operations will be ignored.
 *
 * Because of this, RangeEx allows the user to specifically select pages of a file, even not continuous ones.
 *
 *
 * Extends HashSet of generic Integer, overriding a few methods.
 */
public class RangeEx extends HashSet<Integer> {

    private boolean isUniversal = false;

    /**
     * Constructor that setups RangeEx with a string.
     * @param rangeString a valid RangeEx string.
     */
    public RangeEx(String rangeString) throws ArgumentException {
        this.addAll(this.getValues(rangeString));
    }

    /**
     * Method that parses a valid RangeEx string,
     * converting it into a integer Set (since it should not have repetition).
     * The order is important:
     * Underscore '_' should be handled first,
     * then addition '+',
     * and then subtraction '-'.
     * @return a Set of Integer, with all numbers that conforms to the RangeEx.
     * @throws ArgumentException in case the RangeEx is invalid.
     */
    private Set<Integer> getValues(String rangeString) throws ArgumentException {

        Set<Integer> parsedValues = new HashSet<>();

        if (rangeString.contains("*")) {
            this.isUniversal = true;
            return parsedValues;  // Empty set
        }

        for (String str : RangeEx.getMatches(rangeString, "\\d+_\\d+")) {
            String[] parts = str.split("_");
            for (int i = RangeEx.stringAsUnsigned(parts[0]); i <= RangeEx.stringAsUnsigned(parts[1]); i++) {
                parsedValues.add(i);
            }
        }

        for (String str : RangeEx.getMatches(rangeString, "[+]\\d+")) {
            parsedValues.add(RangeEx.stringAsUnsigned(str));
        }

        for (String str : RangeEx.getMatches(rangeString, "[-]\\d+")) {
            parsedValues.remove(RangeEx.stringAsUnsigned(str));
        }

        return parsedValues;
    }

    /**
     * Static method that converts a string into a int, stripping '-' and '+'.
     * @param str the string to be converted into a int.
     * @return a int that is always positive, not mattering if the input is, for example, "-2".
     * @throws ArgumentException in case the string cannot be represented as a number.
     */
    private static int stringAsUnsigned(String str) throws ArgumentException {
        try {
            return Integer.valueOf(str.replace("-", "").replace("+", ""));
        } catch (NumberFormatException e) {
            throw new ArgumentException();
        }
    }

    /**
     * Static method that is a simple wrapper around RegEx.
     * @param str the string where the RegEx should find matches.
     * @param regex the regex that should be found.
     * @return a Set of Strings, with all matches.
     */
    private static Set<String> getMatches(String str, String regex) {
        Set<String> matches = new HashSet<>();
        Matcher match = Pattern.compile(regex).matcher(str);

        while (match.find()) {
            matches.add(match.group());
        }

        return matches;
    }

    /**
     * Override of `contains`:
     * If isUniversal, every value should return true.
     * Else, check if the value is in the hashSet.
     * @param o the object to check.
     * @return if contains o.
     */
    @Override
    public boolean contains(Object o) {
        if (!(o instanceof Integer)) {
            return false;
        }
        return this.isUniversal || super.contains(o);
    }

    /**
     * Override of `containsAll`:
     * Just calls `contains` in every elem.
     * @param c the Collection to check.
     * @return if contains all the values of c.
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!this.contains(e)) {
                return false;
            }
        }
        return true;
    }

}
