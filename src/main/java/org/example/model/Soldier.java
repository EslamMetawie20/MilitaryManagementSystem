package org.example.model;

import java.util.Date;
import java.util.List;

public class Soldier {
    private String name;
    private String nationalId;
    private String address;
    private String weapon;
    private String phoneNumber;
    private Date birthDate;
    private List<String> relatives; // List of relatives
    private List<String> relativesPhoneNumbers; // Phone numbers of relatives
    private String punishment;
    private String grant;
    private byte[] photo; // To store the soldier's photo as a byte array
    private String barcode;

    // Constructor
    public Soldier(String name, String nationalId, String address, String weapon, String phoneNumber,
                   Date birthDate, List<String> relatives, List<String> relativesPhoneNumbers, String punishment,
                   String grant, byte[] photo, String barcode) {
        this.name = name;
        this.nationalId = nationalId;
        this.address = address;
        this.weapon = weapon;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.relatives = relatives;
        this.relativesPhoneNumbers = relativesPhoneNumbers;
        this.punishment = punishment;
        this.grant = grant;
        this.photo = photo;
        this.barcode = barcode;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<String> relatives) {
        this.relatives = relatives;
    }

    public List<String> getRelativesPhoneNumbers() {
        return relativesPhoneNumbers;
    }

    public void setRelativesPhoneNumbers(List<String> relativesPhoneNumbers) {
        this.relativesPhoneNumbers = relativesPhoneNumbers;
    }

    public String getPunishment() {
        return punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }

    public String getGrant() {
        return grant;
    }

    public void setGrant(String grant) {
        this.grant = grant;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
