package me.pr0crustes.backend.classes.pdf;

import me.pr0crustes.backend.classes.number.RangeEx;
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
     * @param range range that will be used to create the subdocument.
     * @return a PDDocument with the desired pages.
     * @throws NoFileException in case there is a problem with the file.
     * @throws ArgumentException in case there is a problem with the args.
     * @see RangeEx
     */
    public PDDocument subDocument(RangeEx range) throws NoFileException, ArgumentException {
        PDDocument document = PDFManager.getFileDocument(this.file);

        for (int i = document.getNumberOfPages(); i > 0; i--) {
            if (!range.getValues().contains(i)) {
                document.removePage(i - 1); // -1 because user input starts from 1, not 0
            }
        }

        return document;
    }

}
