package me.pr0crustes.backend.classes.pdf;

import me.pr0crustes.backend.exeptions.FileException;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.util.Objects;

/**
 * Class that handles merging PDF
 */
public class PDFMerger {

    private final File[] fileArray;

    /**
     * Constructor, receives a fileArray of files that should be merged.
     * @param fileList the File array of files to be merged.
     */
    public PDFMerger(File[] fileList) {
        this.fileArray = Objects.requireNonNull(fileList);
    }

    /**
     * Method that merges all files.
     * @return a PDDocument, made of all files from fileArray.
     * @throws FileException in case of file error.
     */
    public PDDocument mergeFiles() throws FileException {
        PDDocument finalDocument = new PDDocument();

        for (File currentFile : this.fileArray) {
            if (currentFile != null) {
                PDDocument currentDocument = PDFManager.getFileDocument(currentFile);
                for (int i = 0; i < currentDocument.getNumberOfPages(); i++) {
                    finalDocument.addPage(currentDocument.getPage(i));
                }
            }
        }
        return finalDocument;
    }

}
