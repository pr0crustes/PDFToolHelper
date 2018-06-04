package me.pr0crustes.backend.classes;

import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

class PDFManager {

    static void saveAs(PDDocument document, File saveAs) throws PermissionException {
        try {
            document.save(saveAs);
            document.close();
        } catch (IOException e) {
            throw new PermissionException();
        }
    }

    static PDDocument getFileDocument(File file) throws NoFileException {
        try {
            return PDDocument.load(file);
        } catch (IOException e) {
            throw new NoFileException();
        }
    }
}
