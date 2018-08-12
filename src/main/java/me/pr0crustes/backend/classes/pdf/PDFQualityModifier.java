package me.pr0crustes.backend.classes.pdf;

import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles changing a pdf quality (dpi).
 */
public class PDFQualityModifier {

    private final File file;

    /**
     * Constructor that receives the file to be changed.
     * @param file the File that will get the quality change.
     */
    public PDFQualityModifier(File file) {
        this.file = file;
    }

    /**
     * Method that handles the dpi of the file.
     * @param dpi the target dpi.
     * @return a PDDocument with the new dpi.
     * @throws NoFileException in case of file error.
     * @throws ArgumentException in case of invalid arguments.
     */
    public PDDocument getDocumentWithDPI(int dpi) throws NoFileException, ArgumentException {
        PDDocument originalDocument = PDFManager.getFileDocument(this.file);
        PDFRenderer renderer = new PDFRenderer(originalDocument);

        List<BufferedImage> bufferedImages = new ArrayList<>();

        try {
            for (int i = 0; i < originalDocument.getNumberOfPages(); i++) {
                    BufferedImage pageAsImage = renderer.renderImageWithDPI(i, dpi);
                    bufferedImages.add(pageAsImage);
            }
            originalDocument.close();
        } catch (IOException e) {
            throw new NoFileException();
        }

        PDFCreator pdfCreator = new PDFCreator();
        pdfCreator.addMultipleImageAsPages(bufferedImages);

        return pdfCreator.getDocument();
    }

}
