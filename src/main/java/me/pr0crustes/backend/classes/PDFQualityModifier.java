package me.pr0crustes.backend.classes;

import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PDFQualityModifier {

    private File file;

    public PDFQualityModifier(File file) {
        this.file = file;
    }

    public void saveWithDPI(int dpi, File saveAs) throws NoFileException, PermissionException {

        PDDocument document = this.getDocumentWithDPI(dpi);

        PDFManager.saveAs(document, saveAs);
    }

    private PDDocument getDocumentWithDPI(int dpi) throws NoFileException {

        try {

            PDDocument originalDocument = PDFManager.getFileDocument(this.file);

            PDFRenderer renderer = new PDFRenderer(originalDocument);

            PDDocument newDocument = new PDDocument();

            for (int i = 0; i < originalDocument.getNumberOfPages(); i++) {

                BufferedImage bufferedImage = renderer.renderImageWithDPI(i, dpi);

                PDPage newPage = new PDPage(new PDRectangle(bufferedImage.getWidth(), bufferedImage.getHeight()));

                newDocument.addPage(newPage);

                PDImageXObject pdImage = LosslessFactory.createFromImage(newDocument, bufferedImage);

                PDPageContentStream contentStream = new PDPageContentStream(newDocument, newPage, PDPageContentStream.AppendMode.APPEND, true, true);
                contentStream.drawImage(pdImage, 0, 0);
                contentStream.close();
            }

            originalDocument.close();

            return newDocument;

        } catch (IOException e) {
            throw new NoFileException();
        }
    }
}
