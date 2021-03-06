package me.pr0crustes.backend.classes.file;

import javafx.stage.FileChooser;
import me.pr0crustes.backend.enums.FileExtensions;
import me.pr0crustes.frontend.gui.classes.internationalization.LocalizableStrings;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class made of static methods made to aggregate FileExtension Filter related stuff.
 */
class ExtensionFilterFactory {

    /**
     * Method that transforms a FileExtension Array into a FileChooser.ExtensionFilter array,
     * automatically adds All Files option.
     * @param fileExtensions the FileExtension Array to be converted.
     * @return a FileChooser.ExtensionFilter.
     */
    static List<FileChooser.ExtensionFilter> combineFilters(FileExtensions[] fileExtensions) {
        List<FileChooser.ExtensionFilter> filterList = Arrays.stream(fileExtensions).map(FileExtensions::asFilter).collect(Collectors.toList());
        filterList.add(new FileChooser.ExtensionFilter(LocalizableStrings.ALL_FILES.localized(), "*.*"));
        return filterList;
    }

}
