package me.pr0crustes.backend.classes;

import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import org.apache.pdfbox.pdmodel.PDDocument;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

        PDDocument document = new PDDocument();

        List<BufferedImage> bufferedImages = new ArrayList<>();

        try {

            for (File file : this.fileArray) {
                BufferedImage fileAsImage = ImageIO.read(file);
                bufferedImages.add(fileAsImage);
            }

        } catch (IOException e) {
            throw new NoFileException();
        } catch (NullPointerException e) {
            throw new ArgumentException();
        }

        PDFCreator pdfCreator = new PDFCreator();
        pdfCreator.addMultipleImageAsPages(bufferedImages);

        return pdfCreator.getDocument();
    }
}
