package unit;

import me.pr0crustes.backend.classes.PDFManager;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Test;

public class PDFManagerTest {

    @Test(expected = Exception.class)
    public void testSaveAs() throws Exception {
        PDFManager.saveAs(new PDDocument(), null);
    }

    @Test(expected = Exception.class)
    public void testGetFileDocument() throws Exception {
        PDFManager.getFileDocument(null);
    }

}
