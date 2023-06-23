package ru.flykby.rgr.java_server.models;

public class FormModel {
    private int numberEncrypt;
    private String message;
    private int key;

    public FormModel() {}

    public int getNumberEncrypt() {
        return numberEncrypt;
    }

    public void setNumberEncrypt(int numberEncrypt) {
        this.numberEncrypt = numberEncrypt;
    }
    
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
