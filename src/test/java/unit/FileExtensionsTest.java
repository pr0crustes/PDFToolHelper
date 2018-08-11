package unit;

import me.pr0crustes.backend.enums.FileExtensions;
import org.junit.Assert;
import org.junit.Test;

public class FileExtensionsTest {

    @Test
    public void checkEveryFileExtension() {
        for (FileExtensions fileExtension : FileExtensions.values()) {
            Assert.assertTrue(fileExtension.getExtension().contains("*."));
        }
    }
}
