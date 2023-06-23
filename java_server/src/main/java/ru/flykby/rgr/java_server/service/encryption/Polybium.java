package ru.flykby.rgr.java_server.service.encryption;

public class Polybium implements Encryption {

    public static int toDec(int n) {
        int i = 0;
        int sum = 0;
        while (n > 0) {
            sum += n % 10 * (int) (Math.pow(5, i));
            n /= 10;
            i++;
        }
        return sum;
    }

    public String encrypted(String text, int key) {
        String encryptedText = "";
        text = text.toUpperCase();
        char[] arrayText = text.toCharArray();

        for(int i = 0; i < arrayText.length; i++) {
            
            int n = arrayText[i] - 'A';
            if (arrayText[i] > 'I') {
                n -= 1;
            }
            int n1 = n / 5 + 1;
            int n2 = n % 5 + 1;
            int k = n1 * 10 + n2;
            encryptedText += k + " ";
            

        }

        return encryptedText;
    }

    public String decrypted(String text, int key) {
        String[] words = text.split(" ");
        text = text.toUpperCase();
        char[] arrayText = text.toCharArray();
        int[] arrayEncrypted = new int[words.length];
        for (int i = 0; i < arrayEncrypted.length; i++) {
            arrayEncrypted[i] = Integer.parseInt(words[i]);
        }
        String decryptedText = "";
        
        for (int i = 0; i < arrayEncrypted.length; i++) {

            if (arrayText[i] > 'I'){
                arrayEncrypted[i] += 1;
            }
            decryptedText += (char)(toDec(arrayEncrypted[i]) - 6 + 'A');
             
        }
        return decryptedText;
    }
    
}
