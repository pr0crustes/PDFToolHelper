package me.pr0crustes.backend.classes;

import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.util.Objects;

public class PDFInsert {

    private final File insertFile;
    private final File intoFile;

    public PDFInsert(File insertFile, File intoFile) {
        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
        this.insertFile = Objects.requireNonNull(insertFile);
        this.intoFile = Objects.requireNonNull(intoFile);
    }

    // if entireFile is TRUE, `fromPage` and `toPage` will not be used, so values can be anything
    public PDDocument insertDocument(boolean entireFile, int fromPage, int toPage, int insertAfterPage) throws NoFileException, ArgumentException {

        PDDocument documentInsert;

        if (entireFile) {
            documentInsert = PDFManager.getFileDocument(this.insertFile);
        } else {
            PDFCropper cropper = new PDFCropper(this.insertFile);
            documentInsert = cropper.subDocument(fromPage, toPage);
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
