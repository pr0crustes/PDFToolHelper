package me.pr0crustes.backend.classes;

import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

class PDFCreator {

    private PDDocument document;

    PDFCreator() {
        this(new PDDocument());
    }

    private PDFCreator(PDDocument document) {
        this.document = document;
    }

    PDDocument getDocument() {
        return document;
    }

    void addMultipleImageAsPages(List<BufferedImage> images) throws NoFileException, ArgumentException {
        for (BufferedImage currentImage: images) {
            this.addImageAsPage(currentImage);
        }
    }

    private void addImageAsPage(BufferedImage image) throws NoFileException, ArgumentException {

        PDPage page = PDFManager.pageWithImageSize(image);

        this.document.addPage(page);

        try {

            PDImageXObject pdImageXObject = LosslessFactory.createFromImage(this.document, image);

            PDPageContentStream contentStream = new PDPageContentStream(
                    this.document,
                    page,
                    PDPageContentStream.AppendMode.APPEND,
                    true,
                    true);

            contentStream.drawImage(pdImageXObject, 0, 0);
            contentStream.close();

        } catch (IOException e) {
            throw new NoFileException();
        }

    }
}
