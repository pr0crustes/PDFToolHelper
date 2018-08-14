package me.pr0crustes.backend.classes.pdf;

import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.FileException;
import org.apache.pdfbox.pdmodel.PDDocument;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class that handles image to pdf convertion.
 */
public class PDFConverter {

    private final File[] fileArray;

    /**
     * Constructor that fills important stuff.
     * @param fileArray the array of files that should be converted into a single pdf.
     */
    public PDFConverter(File[] fileArray) {
        this.fileArray = Objects.requireNonNull(fileArray);
    }

    /**
     * Method that converts every file in fileArray and returns a single pdf with everyone.
     * @return a single PDDocument made of all images.
     * @throws FileException in case of file related errors.
     * @throws ArgumentException in case of invalid arguments.
     */
    public PDDocument getDocumentFromImages() throws FileException, ArgumentException {
        List<BufferedImage> bufferedImages = new ArrayList<>();

        try {
            for (File file : this.fileArray) {
                BufferedImage fileAsImage = ImageIO.read(file);
                bufferedImages.add(fileAsImage);
            }
        } catch (IOException e) {
            throw new FileException();
        } catch (NullPointerException e) {
            throw new ArgumentException();
        }

        PDFCreator pdfCreator = new PDFCreator();
        pdfCreator.addMultipleImageAsPages(bufferedImages);

        return pdfCreator.getDocument();
    }

}
