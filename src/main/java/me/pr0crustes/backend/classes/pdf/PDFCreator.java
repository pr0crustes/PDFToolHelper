package me.pr0crustes.backend.classes.pdf;

import me.pr0crustes.backend.exeptions.ArgumentException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * A Class made to reduce code duplicate when manipulating pdf images.
 */
class PDFCreator {

    private final PDDocument document;

    /**
     * Just a constructor that init document with a new document.
     */
    PDFCreator() {
        this.document = new PDDocument();
    }

    /**
     * A Getter for the document.
     * @return PDDocument, the document.
     */
    PDDocument getDocument() {
        return document;
    }

    /**
     * Method that adds all images from a BufferedImage List to the document.
     * @param images the BufferedImage list.
     * @throws IOException in case of file related problems.
     * @throws ArgumentException in case of a invalid argument.
     */
    void addMultipleImageAsPages(List<BufferedImage> images) throws IOException, ArgumentException {
        for (BufferedImage currentImage: images) {
            this.addImageAsPage(currentImage);
        }
    }

    /**
     * Method that adds a single page to the class document.
     * @param image the BufferedImage that should be added.
     * @throws IOException in case of file related problems.
     * @throws ArgumentException in case of an invalid image.
     */
    private void addImageAsPage(BufferedImage image) throws IOException, ArgumentException {
        PDPage page = PDFCreator.pageWithImageSize(image);
        this.document.addPage(page);
        PDImageXObject pdImageXObject = LosslessFactory.createFromImage(this.document, image);
        PDPageContentStream contentStream = new PDPageContentStream(this.document, page, PDPageContentStream.AppendMode.APPEND, true, true);
        contentStream.drawImage(pdImageXObject, 0, 0);
        contentStream.close();
    }

    /**
     * pageWithImageSize is a method that creates a PDPage with size equals to a BufferedImage.
     * @param image a BufferedImage.
     * @return a PDPage with the same size as the image. The image is in no way affected.
     * @throws ArgumentException in case of any problems with the image.
     */
    private static PDPage pageWithImageSize(BufferedImage image) throws ArgumentException {
        try {
            return new PDPage(new PDRectangle(image.getWidth(), image.getHeight()));
        } catch (NullPointerException e) {
            // Image not valid
            throw new ArgumentException();
        }
    }
    
}
