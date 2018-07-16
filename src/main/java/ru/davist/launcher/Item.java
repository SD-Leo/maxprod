package ru.davist.launcher;

import javafx.beans.property.SimpleStringProperty;

public class Item {

    private SimpleStringProperty value;

    public Item(String value) {
        this.value = new SimpleStringProperty(value);
    }

    public String getValue() {
        return value.get();
    }

    public void setValue(String value) {
        this.value.set(value);
    }
}
