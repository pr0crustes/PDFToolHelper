package me.pr0crustes.backend.classes;

import me.pr0crustes.backend.exeptions.NoFileException;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.util.Objects;

public class PDFMerger {

    private final File[] fileList;

    public PDFMerger(File[] fileList) {
        this.fileList = Objects.requireNonNull(fileList);
    }

    public PDDocument mergeFiles() throws NoFileException {

        PDDocument finalDocument = new PDDocument();

        for (File currentFile : this.fileList) {
            PDDocument currentDocument = PDFManager.getFileDocument(currentFile);
            for (int i = 0; i < currentDocument.getNumberOfPages(); i++) {
                finalDocument.addPage(currentDocument.getPage(i));
            }
        }

        return finalDocument;
    }

}
