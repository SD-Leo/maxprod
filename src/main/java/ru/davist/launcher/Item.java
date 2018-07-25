package ru.davist.launcher;

import javafx.beans.property.SimpleStringProperty;
import ru.davist.launcher.model.DesktopEntry;

public class Item {

    private SimpleStringProperty value;


    private DesktopEntry desktopEntry;

    public Item(String value, DesktopEntry desktopEntry) {
        this.value = new SimpleStringProperty(value);
        this.desktopEntry = desktopEntry;
    }

    public Item(String value) {
        this.value = new SimpleStringProperty(value);
    }

    public String getValue() {
        return value.get();
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public DesktopEntry getDesktopEntry() {
        return desktopEntry;
    }

    public void setDesktopEntry(DesktopEntry desktopEntry) {
        this.desktopEntry = desktopEntry;
    }
}
