package me.pr0crustes.backend.classes.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
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
     * @return a PDFDocument, made of all files from fileArray.
     */
    public PDDocument mergeFiles() throws IOException {
        PDDocument finalDocument = new PDDocument();

        for (File currentFile : this.fileArray) {
            PDDocument currentDocument = PDFManager.getFileDocument(currentFile);

            for (int i = 0; i < currentDocument.getNumberOfPages(); i++) {
                finalDocument.addPage(currentDocument.getPage(i));
            }
        }
        return finalDocument;
    }

}
