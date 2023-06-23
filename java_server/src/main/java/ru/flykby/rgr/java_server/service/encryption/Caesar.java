package ru.flykby.rgr.java_server.service.encryption;


public class Caesar implements Encryption {

    public String encrypted(String text, int key) {
        String encryptedText = "";
        for (char ch : text.toCharArray()) {
            encryptedText += (char) ((int)(ch) + key);
        }
        return encryptedText;
    }

    public String decrypted(String text, int key) {
        String decryptedText = "";
        for (char ch : text.toCharArray()) {
            decryptedText += (char) ((int)(ch) - key);
        }
        return decryptedText;
    }
    
}
