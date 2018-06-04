package me.pr0crustes.backend.classes;

import javafx.stage.FileChooser;

class ExtensionFilterFactory {

    static FileChooser.ExtensionFilter newFilter(FileExtensions fileExtension) {
        return new FileChooser.ExtensionFilter(fileExtension.getFilterDescription(), fileExtension.getExtension());
    }
}
