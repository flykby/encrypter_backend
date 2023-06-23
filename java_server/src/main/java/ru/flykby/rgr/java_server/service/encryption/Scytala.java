package ru.flykby.rgr.java_server.service.encryption;


public class Scytala implements Encryption {
    
    public String encrypted(String text, int key) {
        String encryptedText = "";
        char[][] matrix = new char[text.length() / key + (text.length() % key != 0 ? 1 : 0)][key];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                matrix[i][j] = ' ';

        char[] arrayText = text.toCharArray();
        for (int i = 0; i < arrayText.length; i++) {
            matrix[i / key][i % key] = arrayText[i];
        }

        for (int i = 0; i < key; i++) {
            for (int j = 0; j < matrix.length; j++) {
                encryptedText += matrix[j][i];
            }
        }
        
        return encryptedText;
    }

    public String decrypted(String text, int key) {
        String decryptedText = "";
        char[][] matrix = new char[text.length() / key + (text.length() % key != 0 ? 1 : 0)][key];
        char[] arrayText = text.toCharArray();
        int k = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][i] = arrayText[k++];
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[j].length; j++)
                decryptedText += matrix[i][j];
        }
            
        return decryptedText;
    }

}
