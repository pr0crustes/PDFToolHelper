package unit;

import me.pr0crustes.backend.classes.pdf.PDFCropper;
import org.junit.Test;

import java.io.File;

public class PDFCropperTest {

    private final File nonExistentFile = new File("non_existent_file");
    private final PDFCropper cropper = new PDFCropper(nonExistentFile);

    @Test(expected = NullPointerException.class)
    public void testConstructor() {
        new PDFCropper(null);
    }
}
