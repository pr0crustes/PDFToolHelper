package me.pr0crustes.backend.classes.pdf;

import me.pr0crustes.backend.exeptions.ArgumentException;
import org.apache.pdfbox.pdmodel.PDDocument;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class that handles image to pdf conversion.
 */
public class PDFConverter {

    private final List<File> fileList;

    /**
     * Constructor that fills important stuff.
     * @param fileList the array of files that should be converted into a single pdf.
     */
    public PDFConverter(List<File> fileList) {
        this.fileList = Objects.requireNonNull(fileList);
    }

    /**
     * Method that converts every file in fileArray and returns a single pdf with everyone.
     * @return a single PDDocument made of all images.
     * @throws IOException in case of file related errors.
     * @throws ArgumentException in case of invalid arguments.
     */
    public PDDocument getDocumentFromImages() throws IOException, ArgumentException {
        List<BufferedImage> bufferedImages = new ArrayList<>();

        try {
            for (File file : this.fileList) {
                BufferedImage fileAsImage = ImageIO.read(file);
                bufferedImages.add(fileAsImage);
            }
        } catch (NullPointerException e) {
            throw new ArgumentException();
        }

        PDFCreator pdfCreator = new PDFCreator();
        pdfCreator.addMultipleImageAsPages(bufferedImages);

        return pdfCreator.getDocument();
    }

}
