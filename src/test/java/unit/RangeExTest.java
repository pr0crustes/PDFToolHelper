package unit;

import me.pr0crustes.backend.classes.number.RangeEx;
import me.pr0crustes.backend.exeptions.ArgumentException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RangeExTest {

    @Test
    public void testInterval() throws ArgumentException {
        RangeEx range = new RangeEx("1_5");
        Set<Integer> expected = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Assert.assertEquals(expected, range.getValues());
    }

    @Test
    public void testAddition() throws ArgumentException {
        RangeEx range = new RangeEx("1_2+5+7");
        Set<Integer> expected = new HashSet<>(Arrays.asList(1, 2, 5, 7));
        Assert.assertEquals(expected, range.getValues());
    }

    @Test
    public void testSubtraction() throws ArgumentException {
        RangeEx range = new RangeEx("1_5-3");
        Set<Integer> expected = new HashSet<>(Arrays.asList(1, 2, 4, 5));
        Assert.assertEquals(expected, range.getValues());
    }

}
