package unit;

import me.pr0crustes.backend.classes.number.RangeEx;
import me.pr0crustes.backend.classes.pdf.PDFCropper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

public class CropTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();


    @Test
    public void testCrop() throws Exception {

        File firstFile = TestFileUtils.getTestFile("sample_1-10_pdf.pdf");

        // Crop
        PDFCropper cropper = new PDFCropper(firstFile);

        PDDocument document1 = cropper.subDocument(new RangeEx("1_3"));
        PDDocument document2 = cropper.subDocument(new RangeEx("+8"));

        document1.save(this.folder.newFile("document1.pdf"));
        document2.save(this.folder.newFile("document2.pdf"));

    }

}
