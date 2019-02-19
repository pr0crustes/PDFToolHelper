package unit;

import me.pr0crustes.backend.classes.number.RangeEx;
import me.pr0crustes.backend.exeptions.ArgumentException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class RangeExTest {

    private void testRangeExString(String str, Integer... values) throws ArgumentException {
        Assert.assertTrue(new RangeEx(str).containsAll(Arrays.asList(values)));
    }

    @Test
    public void testInterval() throws ArgumentException {
        this.testRangeExString("1_5", 1, 2, 3, 4, 5);
    }

    @Test
    public void testAddition() throws ArgumentException {
        this.testRangeExString("1_2+5+7", 1, 2, 5, 7);
    }

    @Test
    public void testSubtraction() throws ArgumentException {
        this.testRangeExString("1_5-3", 1, 2, 4, 5);
    }

    @Test
    public void testUniversal() throws ArgumentException {
        this.testRangeExString("*-2", 1, 2, 4, 5);
    }

}
