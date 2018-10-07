package me.pr0crustes.frontend.gui.classes.elements;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.List;
import java.util.Objects;

/**
 * Class that handles, with the use of Generics, a ListView and a ObservableList,
 * creating a JavaFX listView and allowing for manipulation without knowing the type.
 * @param <T> the ListView and ObservableList type.
 */
public class ListViewManager <T> {

    private final ListView<T> listView;
    private final ObservableList<T> items = FXCollections.observableArrayList();

    /**
     * Constructor that fills listView.
     * @param list a ListView with the same type as T.
     */
    ListViewManager(ListView<T> list) {
        this.listView = Objects.requireNonNull(list);
        this.listView.setItems(this.items);
    }

    /**
     * Method that adds an object to the ObservableList.
     * @param objects an List with anything of type T.
     */
    void addObjects(List<T> objects) {
        if (objects != null) {
            for (T object : objects) {
                if (object != null) {  // Keep this null check! If an object is null it should not be added!
                    this.items.add(object);
                }
            }
        }
    }

    /**
     * Method that removes the selected item at listView from the list.
     */
    void removeSelected() {
        int selectedIndex = this.listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            this.items.remove(selectedIndex);
            this.listView.getSelectionModel().clearSelection();
        }
    }

    /**
     * Method that moves the selected item up, if possible.
     */
    void moveSelectedUp() {
        int selectedIndex = this.listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex > 0) {
            this.moveListEntry(selectedIndex, selectedIndex - 1);
        }
    }

    /**
     * Method that moves the selected item down, if possible.
     */
    void moveSelectedDown() {
        int selectedIndex = this.listView.getSelectionModel().getSelectedIndex();

        if (selectedIndex != this.items.size() - 1) {
            this.moveListEntry(selectedIndex, selectedIndex + 1);
        }
    }

    /**
     * Method that facilitates moving items in the list.
     * @param currentIndex the current item index.
     * @param newIndex the new item index.
     */
    private void moveListEntry(int currentIndex, int newIndex) {
        this.switchItemPositions(currentIndex, newIndex);
        this.listView.getSelectionModel().select(newIndex);
    }


    /**
     * Method that switch the position of two items at the list.
     * @param pos1 the position of the first item.
     * @param pos2 the position of the second item.
     */
    private void switchItemPositions(int pos1, int pos2) {
        T fileAtPos1 = this.items.get(pos1);
        T fileAtPos2 = this.items.get(pos2);
        this.items.set(pos1, fileAtPos2);
        this.items.set(pos2, fileAtPos1);
    }

    /**
     * Method that returns a List version of items.
     * @return a List with the items of items. Changing this list will not affect items.
     */
    public List<T> getList() {
        return this.items.subList(0, this.items.size());
    }

}
