package unit;

import me.pr0crustes.backend.classes.Numbers;
import org.junit.Assert;
import org.junit.Test;

public class NumbersTest {

    @Test
    void isBetweenTest() {
        Assert.assertTrue(Numbers.isBetween(10, 0, 20));
        Assert.assertTrue(Numbers.isBetween(-8, -10, -5));
        Assert.assertFalse(Numbers.isBetween(10, 19, 20));
        Assert.assertFalse(Numbers.isBetween(-10, -20, -30));
    }
}
