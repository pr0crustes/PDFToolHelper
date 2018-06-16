package me.pr0crustes.frontend.gui.classes.elements;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.List;
import java.util.Objects;

public class ListViewManager <T> {

    private ListView<T> listView;

    private ObservableList<T> items = FXCollections.observableArrayList();

    public ListViewManager(ListView<T> list) {
        this.listView = Objects.requireNonNull(list);
        this.listView.setItems(this.items);
    }

    public void addObject(T object) {
        if (object != null) {
            this.items.add(object);
        }
    }

    public void removeSelected() {
        int selectedIndex = this.listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex > - 1) {
            this.items.remove(selectedIndex);
            this.listView.getSelectionModel().clearSelection();
        }
    }

    public void moveSelectedUp() {
        int selectedIndex = this.listView.getSelectionModel().getSelectedIndex();

        if (selectedIndex > 0) {
            this.moveListEntry(selectedIndex, selectedIndex - 1);
        }
    }

    public void moveSelectedDown() {
        int selectedIndex = this.listView.getSelectionModel().getSelectedIndex();

        if (selectedIndex != this.items.size() - 1) {
            this.moveListEntry(selectedIndex, selectedIndex + 1);
        }
    }

    private void moveListEntry(int currentIndex, int newIndex) {
        this.switchFilePositions(currentIndex, newIndex);
        this.listView.getSelectionModel().select(newIndex);
    }


    private void switchFilePositions(int pos1, int pos2) {
        T fileAtPos1 = this.items.get(pos1);
        T fileAtPos2 = this.items.get(pos2);
        this.items.set(pos1, fileAtPos2);
        this.items.set(pos2, fileAtPos1);
    }

    public List<T> getList() {
        return this.items.subList(0, this.items.size());
    }

}
