package unit;

import me.pr0crustes.backend.classes.PDFManager;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Test;

public class PDFManagerTest {

    @Test(expected = PermissionException.class)
    void testSaveAs() throws Exception {
        PDFManager.saveAs(new PDDocument(), null);
    }

    @Test(expected = NoFileException.class)
    void testGetFileDocument() throws Exception {
        PDFManager.getFileDocument(null);
    }

}
