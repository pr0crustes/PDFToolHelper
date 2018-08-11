package me.pr0crustes.backend.classes;

import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.util.Objects;

public class PDFCropper {

    private final File file;

    public PDFCropper(File file) {
        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
        this.file = Objects.requireNonNull(file);
    }

    public PDDocument subDocument(int fromPage, int toPage) throws NoFileException, ArgumentException {
        PDDocument document = PDFManager.getFileDocument(this.file);

        if ((fromPage >= 1 && toPage <= document.getNumberOfPages()) && (fromPage <= toPage)) {
            return this.splitDocument(document, fromPage, toPage);
        }
        // Invalid parameters
        throw new ArgumentException();
    }

    private PDDocument splitDocument(PDDocument document, int fromPage, int toPage) {
        for (int i = document.getNumberOfPages() - 1 ; i >= 0 ; i--) {
            if (Numbers.isBetween(i, fromPage - 1, toPage - 1)) { // -1 because page count starts from 0 but user input from 1
                continue;
            }
            document.removePage(i);
        }
        return document;
    }

}
