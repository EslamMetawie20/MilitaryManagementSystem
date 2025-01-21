package org.example.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SoldierRow {
    private StringProperty name;
    private StringProperty id;
    private StringProperty address;
    private StringProperty weapon;
    private StringProperty phone;
    private StringProperty relatives;
    private StringProperty punishment;
    private StringProperty grant;
    private StringProperty militaryNumber;

    public SoldierRow(String name, String id, String address, String weapon, String phone,
                      String relatives, String punishment, String grant, String militaryNumber) {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleStringProperty(id);
        this.address = new SimpleStringProperty(address);
        this.weapon = new SimpleStringProperty(weapon);
        this.phone = new SimpleStringProperty(phone);
        this.relatives = new SimpleStringProperty(relatives);
        this.punishment = new SimpleStringProperty(punishment);
        this.grant = new SimpleStringProperty(grant);
        this.militaryNumber = new SimpleStringProperty(militaryNumber);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty weaponProperty() {
        return weapon;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty relativesProperty() {
        return relatives;
    }

    public StringProperty punishmentProperty() {
        return punishment;
    }

    public StringProperty grantProperty() {
        return grant;
    }

    public StringProperty militaryNumberProperty() {
        return militaryNumber;
    }
}
