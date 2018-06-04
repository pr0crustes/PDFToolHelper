package me.pr0crustes.backend.classes;

import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.util.Objects;

public class PDFCropper {

    private File file;

    public PDFCropper(File file) {
        this.file = Objects.requireNonNull(file);
    }

    public void cropDocument(int fromPage, int toPage, File saveAs) throws NoFileException, PermissionException, ArgumentException {

        saveAs = Objects.requireNonNull(saveAs);

        PDDocument document = PDFManager.getFileDocument(file);

        if ((fromPage >= 1 && toPage <= document.getNumberOfPages()) && (fromPage <= toPage)) {
            PDDocument partial = this.splitDocument(document, fromPage, toPage);
            PDFManager.saveAs(partial, saveAs);
        } else {
            // Invalid parameters
            throw new ArgumentException();
        }

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
