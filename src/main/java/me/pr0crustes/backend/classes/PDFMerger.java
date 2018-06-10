package me.pr0crustes.backend.classes;

import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class PDFMerger {

    private File[] fileList;

    public PDFMerger(File[] fileList) {
        this.fileList = Objects.requireNonNull(fileList);
    }

    public void mergeFiles(File saveAs) throws NoFileException, PermissionException {

        PDFMergerUtility merger = this.addSources();

        merger.setDestinationFileName(saveAs.getPath());

        this.saveMerger(merger);
    }

    private PDFMergerUtility addSources() throws NoFileException {
        try {
            PDFMergerUtility merger = new PDFMergerUtility();

            for (File currentFile : fileList) {
                merger.addSource(currentFile);
            }

            return merger;
        } catch (IOException e) {
            throw new NoFileException();
        }
    }

    private void saveMerger(PDFMergerUtility merger) throws PermissionException {
        try {
            merger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
        } catch (IOException e) {
            throw new PermissionException();
        }
    }
}
