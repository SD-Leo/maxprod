package ru.davist.launcher.model;

import javafx.beans.property.SimpleStringProperty;

public class Item {

    private SimpleStringProperty value;

    private SimpleStringProperty exec;

    private DesktopEntry desktopEntry;

    public Item(String value, DesktopEntry desktopEntry) {
        this.value = new SimpleStringProperty(value);
        this.exec = new SimpleStringProperty(desktopEntry.getExec());
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

    public String getExec() {
        return exec.get();
    }

    public void setExec(String exec) {
        this.exec.set(exec);
    }

    public DesktopEntry getDesktopEntry() {
        return desktopEntry;
    }

    public void setDesktopEntry(DesktopEntry desktopEntry) {
        this.desktopEntry = desktopEntry;
    }
}
