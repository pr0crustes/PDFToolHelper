package me.pr0crustes.backend.classes;

import javafx.stage.FileChooser;

import java.util.ArrayList;
import java.util.List;

class ExtensionFilterFactory {

    static FileChooser.ExtensionFilter newFilter(FileExtensions fileExtension) {
        return new FileChooser.ExtensionFilter(fileExtension.getFilterDescription(), fileExtension.getExtension());
    }

    static FileChooser.ExtensionFilter[] combineFilters(FileExtensions[] fileExtensions) {
        List<FileChooser.ExtensionFilter> filterList = new ArrayList<>();

        for (FileExtensions extension : fileExtensions) {
            filterList.add(newFilter(extension));
        }

        filterList.add(new FileChooser.ExtensionFilter("All Files", "*.*"));


        FileChooser.ExtensionFilter[] filterArray = new FileChooser.ExtensionFilter[filterList.size()];
        filterArray = filterList.toArray(filterArray);

        return filterArray;
    }
}
