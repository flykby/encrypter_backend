package ru.flykby.rgr.java_server.service.encryption;

public interface Encryption {
    
    public String encrypted(String text, int key);

    public String decrypted(String text, int key);

}
