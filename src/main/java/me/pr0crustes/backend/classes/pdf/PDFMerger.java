package me.pr0crustes.backend.classes.pdf;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Class that handles merging PDF
 */
public class PDFMerger {

    private final List<File> fileArray;

    /**
     * Constructor, receives a fileArray of files that should be merged.
     * @param fileList the File list of files to be merged.
     */
    public PDFMerger(List<File> fileList) {
        this.fileArray = Objects.requireNonNull(fileList);
    }

    /**
     * Merge all files in fileArray.
     * @param saveAs the file to save the merge as.
     * @throws IOException in case there is any problem with reading / writing to files.
     */
    public void mergeFiles(File saveAs) throws IOException {
        PDFMergerUtility merger = new PDFMergerUtility();

        PDDocument finalDocument = new PDDocument();
        for (File currentFile : this.fileArray) {
            merger.addSource(currentFile);
        }

        merger.setDestinationFileName(saveAs.getAbsolutePath());
        merger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
    }

}
