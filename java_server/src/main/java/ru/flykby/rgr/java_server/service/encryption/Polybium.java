package ru.flykby.rgr.java_server.service.encryption;

public class Polybium implements Encryption {

    private char[][] matrix = {
        {'A', 'B', 'C', 'D', 'E'},
        {'F', 'G', 'H', 'I', 'J'},
        {'K', 'L', 'M', 'N', 'O'},
        {'P', 'Q', 'R', 'S', 'T'},
        {'U', 'V', 'W', 'X', 'Y'},
        {'Z', ' ', '.', '?', '!'}
    };

    public String encrypted(String text, int key) {
        String encryptedText = "";
        text = text.toUpperCase();
        char[] arrayText = text.toCharArray();

        for(int i = 0; i < arrayText.length; i++) {
            
            for (int x = 0; x < matrix.length; x++) {
                for (int y = 0; y < matrix[x].length; y++) {
                    if (arrayText[i] == matrix[x][y]) {
                        encryptedText += String.format("%d ", (x + 1) * 10 + y + 1);
                    }
                }
            }
            
        }

        return encryptedText;
    }

    public String decrypted(String text, int key) {
        String[] words = text.split(" ");
        text = text.toUpperCase();
        int[] arrayEncrypted = new int[words.length];
        for (int i = 0; i < arrayEncrypted.length; i++) {
            arrayEncrypted[i] = Integer.parseInt(words[i]);
        }
        String decryptedText = "";
        
        for (int i = 0; i < arrayEncrypted.length; i++) {

            decryptedText += matrix[arrayEncrypted[i] / 10 - 1][arrayEncrypted[i] % 10 - 1];
            // System.out.print(arrayEncrypted[i] / 10 + " ");
             
        }
        return decryptedText;
    }
    
}
