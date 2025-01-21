package org.example.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SoldierRow {
    private StringProperty name;
    private StringProperty id;
    private StringProperty weapon;
    private StringProperty phone;

    public SoldierRow(String name, String id, String weapon, String phone) {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleStringProperty(id);
        this.weapon = new SimpleStringProperty(weapon);
        this.phone = new SimpleStringProperty(phone);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty weaponProperty() {
        return weapon;
    }

    public StringProperty phoneProperty() {
        return phone;
    }
}
