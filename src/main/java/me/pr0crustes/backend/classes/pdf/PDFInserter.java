package me.pr0crustes.backend.classes.pdf;

import me.pr0crustes.backend.classes.number.RangeEx;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Class that handles inserting a pdf (or a subpart of one) into other pdf.
 */
public class PDFInserter {

    private final File insertFile;
    private final File intoFile;

    /**
     * Constructor that fills important stuff.
     * @param insertFile the file that should be inserted.
     * @param intoFile the file to be inserted into.
     */
    public PDFInserter(File insertFile, File intoFile) {
        this.insertFile = Objects.requireNonNull(insertFile);
        this.intoFile = Objects.requireNonNull(intoFile);
    }

    /**
     * Method that inserts the insertFile into intoFile, respecting the args.
     * @param range the range representing the pages that should be inserted.
     * @param insertAfterPage after which page the file should be included.
     * @return a PDDocument with the insert done.
     * @throws IOException in case of file error.
     */
    public PDDocument insertDocument(RangeEx range, int insertAfterPage) throws IOException {
        PDFCropper cropper = new PDFCropper(this.insertFile);

        try (PDDocument documentInsert = cropper.subDocument(range);
             PDDocument documentInto = PDDocument.load(this.intoFile)) {
            return this.insertDocumentInto(documentInsert, documentInto, insertAfterPage);
        }
    }

    /**
     * Private method that inserts one document into other.
     * Does all the test to know where it should be added.
     * @param documentInsert the document to insert.
     * @param documentInto the document to insert into.
     * @param afterPage after what page it should be added.
     * @return the PDDocument with the insertion done.
     */
    private PDDocument insertDocumentInto(PDDocument documentInsert, PDDocument documentInto, int afterPage) {
        PDDocument documentNew = new PDDocument();

        for (int i = 0; i < documentInto.getNumberOfPages() + 1; i++) {
            if (i == afterPage) {  // Should insert now.
                for (int j = 0; j < documentInsert.getNumberOfPages(); j++) {
                    documentNew.addPage(documentInsert.getPage(j));
                }
            }
            if (i < documentInto.getNumberOfPages()) {
                documentNew.addPage(documentInto.getPage(i));
            }
        }
        return documentNew;
    }

}
