package me.pr0crustes.backend.classes;

import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class PDFConverter {

    private final File[] fileArray;

    public PDFConverter(File[] fileArray) {
        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
        this.fileArray = Objects.requireNonNull(fileArray);
    }

    public void convertToPDF(File saveAs) throws NoFileException, PermissionException, ArgumentException {

        saveAs = Objects.requireNonNull(saveAs);

        PDDocument document = this.getDocumentFromImage();

        PDFManager.saveAs(document, saveAs);

    }

    private PDDocument getDocumentFromImage() throws NoFileException, ArgumentException {

        try {

            PDDocument document = new PDDocument();

            for (File file : this.fileArray) {

                BufferedImage bufferedImage = ImageIO.read(file);

                PDPage page = new PDPage(new PDRectangle(bufferedImage.getWidth(), bufferedImage.getHeight()));

                document.addPage(page);

                PDImageXObject pdImageXObject = LosslessFactory.createFromImage(document, bufferedImage);

                PDPageContentStream contentStream = new PDPageContentStream(
                        document,
                        page,
                        PDPageContentStream.AppendMode.APPEND,
                        true,
                        true);

                contentStream.drawImage(pdImageXObject, 0, 0);
                contentStream.close();

            }

            return document;

        } catch (IOException e) {
            throw new NoFileException();
        } catch (NullPointerException e) {
            throw new ArgumentException();
        }
    }


}
