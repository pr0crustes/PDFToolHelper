package unit;

import me.pr0crustes.backend.classes.PDFCropper;
import org.junit.Test;

import java.io.File;

public class PDFCropperTest {

    private File nonExistentFile = new File("non_existent_file");
    private PDFCropper cropper = new PDFCropper(nonExistentFile);

    @Test(expected = NullPointerException.class)
    public void testConstructor() {
        new PDFCropper(null);
    }

    @Test(expected = Exception.class)
    public void testNegativeArguments() throws Exception {
        cropper.cropDocument(-10, -6, nonExistentFile);
    }

    @Test(expected = NullPointerException.class)
    public void testNullFile() throws Exception {
        cropper.cropDocument(1, 2, null);
    }
}
