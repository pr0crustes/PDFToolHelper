package me.pr0crustes.backend.classes;

import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDFQualityModifier {

    private final File file;

    public PDFQualityModifier(File file) {
        this.file = file;
    }

    public PDDocument getDocumentWithDPI(int dpi) throws NoFileException, ArgumentException {

        PDDocument originalDocument = PDFManager.getFileDocument(this.file);
        PDFRenderer renderer = new PDFRenderer(originalDocument);

        List<BufferedImage> bufferedImages = new ArrayList<>();

        try {
            for (int i = 0; i < originalDocument.getNumberOfPages(); i++) {
                    BufferedImage pageAsImage = renderer.renderImageWithDPI(i, dpi);
                    bufferedImages.add(pageAsImage);
            }
        } catch (IOException e) {
            throw new NoFileException();
        }

        PDFCreator pdfCreator = new PDFCreator();
        pdfCreator.addMultipleImageAsPages(bufferedImages);

        return pdfCreator.getDocument();
    }
}
