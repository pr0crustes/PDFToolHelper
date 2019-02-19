package me.pr0crustes.backend.classes.pdf;

import me.pr0crustes.backend.exeptions.ArgumentException;
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
     * @throws IOException in case of file error.
     * @throws ArgumentException in case of invalid arguments.
     */
    public PDDocument getDocumentWithDPI(int dpi) throws IOException, ArgumentException {
        List<BufferedImage> bufferedImages = new ArrayList<>();

        try (PDDocument originalDocument = PDDocument.load(this.file)) {
            PDFRenderer renderer = new PDFRenderer(originalDocument);
            for (int i = 0; i < originalDocument.getNumberOfPages(); i++) {
                BufferedImage asImage = renderer.renderImageWithDPI(i, dpi);
                bufferedImages.add(asImage);
            }
        }

        PDFCreator pdfCreator = new PDFCreator();
        pdfCreator.addMultipleImageAsPages(bufferedImages);
        return pdfCreator.getDocument();
    }

}
