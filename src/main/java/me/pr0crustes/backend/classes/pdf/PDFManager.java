package me.pr0crustes.backend.classes.pdf;

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

/**
 * PDFManager is a class made of static methods made to aggregate common operations.
 */
public class PDFManager {

    /**
     * saveAs is a method that handles the saving of a PDDocument, throwing PermissionException in case of a IOException.
     * @param document the PDDocument that should be save.
     * @param saveAs the File that the document should be saved as.
     * @throws PermissionException in case of a IOException.
     */
    public static void saveAs(PDDocument document, File saveAs) throws PermissionException {
        try {
            document.save(Objects.requireNonNull(saveAs));
            document.close();
        } catch (IOException e) {
            throw new PermissionException();
        }
    }

    /**
     * getFileDocument is a method that receiving a File, returns the corresponding PDDocument.
     * this is useful because PDDocument.load forces you into handling IOException, leading to repetitive code.
     * @param file the PDDocument File.
     * @return the file as a PDDocument, if possible.
     * @throws NoFileException in case of IOException.
     */
    public static PDDocument getFileDocument(File file) throws NoFileException {
        try {
            return PDDocument.load(Objects.requireNonNull(file));
        } catch (IOException e) {
            throw new NoFileException();
        }
    }

    /**
     * pageWithImageSize is a method that creates a PDPage with size equals to a BufferedImage.
     * @param image a BufferedImage.
     * @return a PDPage with the same size as the image. The image is in no way affected.
     * @throws ArgumentException in case of any problems with the image.
     */
    public static PDPage pageWithImageSize(BufferedImage image) throws ArgumentException {
        try {
            return new PDPage(new PDRectangle(image.getWidth(), image.getHeight()));
        } catch (NullPointerException e) {
            // Image not valid
            throw new ArgumentException();
        }
    }

}
