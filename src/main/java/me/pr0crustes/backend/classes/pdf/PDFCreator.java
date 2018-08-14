package me.pr0crustes.backend.classes.pdf;

import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.FileException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * A Class made to reduce code duplicate when manipulating pdf images.
 */
class PDFCreator {

    private PDDocument document;

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
     * @throws FileException in case of file related problems.
     * @throws ArgumentException in case of a invalid argument.
     */
    void addMultipleImageAsPages(List<BufferedImage> images) throws FileException, ArgumentException {
        for (BufferedImage currentImage: images) {
            this.addImageAsPage(currentImage);
        }
    }

    /**
     * Method that adds a single page to the class document.
     * @param image the BufferedImage that should be added.
     * @throws FileException in case of file related problems.
     * @throws ArgumentException in case of an invalid image.
     */
    private void addImageAsPage(BufferedImage image) throws FileException, ArgumentException {
        PDPage page = PDFManager.pageWithImageSize(image);
        this.document.addPage(page);
        try {
            PDImageXObject pdImageXObject = LosslessFactory.createFromImage(this.document, image);
            PDPageContentStream contentStream = new PDPageContentStream(this.document, page, PDPageContentStream.AppendMode.APPEND, true, true);
            contentStream.drawImage(pdImageXObject, 0, 0);
            contentStream.close();
        } catch (IOException e) {
            throw new FileException();
        }
    }
    
}
