package me.pr0crustes.backend.classes.pdf;

import me.pr0crustes.backend.classes.number.RangeEx;
import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.FileException;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.util.Objects;

/**
 * Class that handles inserting a pdf (or a subpart of one) into other pdf.
 */
public class PDFInsert {

    private final File insertFile;
    private final File intoFile;

    /**
     * Constructor that fills important stuff.
     * @param insertFile the file that should be inserted.
     * @param intoFile the file to be inserted into.
     */
    public PDFInsert(File insertFile, File intoFile) {
        this.insertFile = Objects.requireNonNull(insertFile);
        this.intoFile = Objects.requireNonNull(intoFile);
    }

    /**
     * Method that inserts the insertFile into intoFile, respecting the args.
     * @param entireFile if all file should be included. If TRUE, `fromPage` and `toPage` will not be used, so values can be anything.
     * @param range the range representing the pages that should be inserted.
     * @param insertAfterPage after which page the file should be included.
     * @return a PDDocument with the insert done.
     * @throws FileException in case of file error.
     * @throws ArgumentException in case of invalid argument.
     */
    public PDDocument insertDocument(boolean entireFile, RangeEx range, int insertAfterPage) throws FileException, ArgumentException {
        PDDocument documentInsert;

        if (entireFile) {
            documentInsert = PDFManager.getFileDocument(this.insertFile);
        } else {
            PDFCropper cropper = new PDFCropper(this.insertFile);
            documentInsert = cropper.subDocument(range);
        }

        PDDocument documentInto = PDFManager.getFileDocument(this.intoFile);
        PDDocument documentNew = new PDDocument();

        for (int i = 0; i < documentInto.getNumberOfPages(); i++) {
            if (i == insertAfterPage) {
                for (int j = 0; j < documentInsert.getNumberOfPages(); j++) {
                    documentNew.addPage(documentInsert.getPage(j));
                }
            }
            documentNew.addPage(documentInto.getPage(i));
        }

        // Check if should be added at the end
        if (insertAfterPage == documentInto.getNumberOfPages()) {
            for (int j = 0; j < documentInsert.getNumberOfPages(); j++) {
                documentNew.addPage(documentInsert.getPage(j));
            }
        }

        return documentNew;
    }

}
