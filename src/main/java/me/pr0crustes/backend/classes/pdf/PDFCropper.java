package me.pr0crustes.backend.classes.pdf;

import me.pr0crustes.backend.classes.number.Numbers;
import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.util.Objects;

/**
 * PDFCropper is a class that handles creating a subdocument from a PDF.
 */
public class PDFCropper {

    private final File file;

    /**
     * Constructor that setups the needed File, testing if not null.
     * @param file the file that we want a subdocument from.
     */
    public PDFCropper(File file) {
        this.file = Objects.requireNonNull(file);
    }

    /**
     * The most important class method.
     * Creates a subdocument based on the args.
     * @param fromPage the page where the subdocument should start.
     * @param toPage the page where the subdocument should end (inclusive).
     * @return a PDDocument with the desired pages.
     * @throws NoFileException in case there is a problem with the file.
     * @throws ArgumentException in case there is a problem with the args.
     */
    public PDDocument subDocument(int fromPage, int toPage) throws NoFileException, ArgumentException {
        PDDocument document = PDFManager.getFileDocument(this.file);

        if ((fromPage >= 1 && toPage <= document.getNumberOfPages()) && (fromPage <= toPage)) {
            return this.splitDocument(document, fromPage, toPage);
        }
        throw new ArgumentException();
    }

    /**
     * Method that removes pages from a document.
     * @param document the document.
     * @param fromPage first page to not be removed.
     * @param toPage last page to not be removed.
     * @return a PDDocument withou that pages.
     */
    private PDDocument splitDocument(PDDocument document, int fromPage, int toPage) {
        for (int i = document.getNumberOfPages() - 1 ; i >= 0 ; i--) {
            if (!Numbers.isBetween(i, fromPage - 1, toPage - 1)) { // -1 because page count starts from 0 but user input from 1
                document.removePage(i);
            }
        }
        return document;
    }

}
