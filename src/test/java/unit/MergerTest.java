package unit;

import me.pr0crustes.backend.classes.number.RangeEx;
import me.pr0crustes.backend.classes.pdf.PDFCropper;
import me.pr0crustes.backend.classes.pdf.PDFMerger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MergerTest {

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testMerger() throws Exception {

        File firstFile = TestFileUtils.getTestFile("sample_1-10_pdf.pdf");

        // Crop
        PDFCropper cropper = new PDFCropper(firstFile);

        PDDocument document1 = cropper.subDocument(new RangeEx("1_3"));
        PDDocument document2 = cropper.subDocument(new RangeEx("+8"));

        document1.save(this.folder.newFile("document1.pdf"));
        document2.save(this.folder.newFile("document2.pdf"));

        File file1 = Paths.get(folder.getRoot().getPath(), "document1.pdf").toFile();
        File file2 = Paths.get(folder.getRoot().getPath(), "document2.pdf").toFile();

        List<File> toMerge = new ArrayList<>();
        toMerge.add(file1);
        toMerge.add(file2);

        PDFMerger merger = new PDFMerger(toMerge);
        PDDocument document = merger.mergeFiles();

        document.save(new File("src/test/resources/result/merged1.pdf"));

    }

}
