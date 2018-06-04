package unit;

import me.pr0crustes.backend.classes.PDFCropper;
import me.pr0crustes.backend.exeptions.ArgumentException;
import org.junit.Test;

import java.io.File;

public class PDFCropperTest {

    private File nonExistentFile = new File("non_existent_file");
    private PDFCropper cropper = new PDFCropper(nonExistentFile);

    @Test(expected = NullPointerException.class)
    void testConstructor() {
        new PDFCropper(null);
    }

    @Test(expected = ArgumentException.class)
    void testNegativeArguments() throws Exception {
        cropper.cropDocument(-10, -6, nonExistentFile);
    }

    @Test(expected = NullPointerException.class)
    void testNullFile() throws Exception {
        cropper.cropDocument(1, 2, null);
    }
}
