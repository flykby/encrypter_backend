package ru.flykby.rgr.java_server.service.encryption;

public class Atbash implements Encryption {

    public String encrypted(String text, int key) {
        String encryptedText = "";

        char[] arrayText = text.toCharArray();
        for ( int i = 0; i < arrayText.length; i++) {
            if ('A' <= arrayText[i] && arrayText[i] <= 'Z'){
                encryptedText += (char) ('Z' - (arrayText[i] - 'A'));
            } else if ('a' < arrayText[i] && arrayText[i] < 'z') {
                encryptedText += (char) ('z' - (arrayText[i] - 'a'));
            } else {
                encryptedText += arrayText[i];
            }
          
        }

        return encryptedText;
    }
    
    public String decrypted(String text, int key) {
        String decryptedText = "";
        
        char[] arrayText = text.toCharArray();
        for ( int i = 0; i < arrayText.length; i++) {
            if ('A' <= arrayText[i] && arrayText[i] <= 'Z'){
                decryptedText += (char) ('Z' - (arrayText[i] - 'A'));
            } else if ('a' < arrayText[i] && arrayText[i] < 'z') {
                decryptedText += (char) ('z' - (arrayText[i] - 'a'));
            } else {
                decryptedText += arrayText[i];
            }
            
        }
    
        return decryptedText;
    }

    
}
