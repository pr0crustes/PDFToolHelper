package unit;

import me.pr0crustes.backend.classes.FileExtensions;
import org.junit.Assert;
import org.junit.Test;

public class FileExtensionsTest {

    @Test
    void checkEveryFileExtension() {
        for (FileExtensions fileExtension : FileExtensions.values()) {
            Assert.assertTrue(fileExtension.getExtension().contains("*."));
        }
    }
}
