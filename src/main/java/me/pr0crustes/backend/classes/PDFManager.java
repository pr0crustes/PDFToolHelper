package me.pr0crustes.backend.classes;

import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class PDFManager {

    public static void saveAs(PDDocument document, File saveAs) throws PermissionException {
        try {
            document.save(Objects.requireNonNull(saveAs));
            document.close();
        } catch (IOException e) {
            throw new PermissionException();
        }
    }

    public static PDDocument getFileDocument(File file) throws NoFileException {
        try {
            return PDDocument.load(Objects.requireNonNull(file));
        } catch (IOException e) {
            throw new NoFileException();
        }
    }

    public static PDPage pageWithImageSize(BufferedImage image) throws ArgumentException {
        try {
            return new PDPage(new PDRectangle(image.getWidth(), image.getHeight()));
        } catch (NullPointerException e) {
            // Image not valid
            throw new ArgumentException();
        }
    }

}
