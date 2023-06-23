package ru.flykby.rgr.java_server.service.encryption;

public class Matrix implements Encryption {
    
    public String encrypted(String text, int key) {
        String encryptedText = "";

        char[] arrayText = text.toCharArray();
        int matrixLength = (int) Math.sqrt(arrayText.length) + 1;
        char[][] matrix = new char[matrixLength][matrixLength];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                matrix[i][j] = ' ';

        for (int i = 0; i < arrayText.length; i++) {
            matrix[i / matrixLength][i % matrixLength] = arrayText[i];
        }

        for (int i = 0; i < matrixLength / 2 + 1; i++) {
            for (int j = i; j < matrixLength - i; j++) {
                encryptedText += matrix[i][j]; // вправо
            }
            for (int j = i + 1; j < matrixLength - i; j++) {
                encryptedText += matrix[j][matrixLength - i - 1]; // вниз
            }
            for (int j = i + 1; j < matrixLength - i - 1; j++) {
                encryptedText += matrix[matrixLength - i - 1][matrixLength - j - 1]; // влево
            }
            for (int j = i + 1; j < matrixLength - i - 1; j++) {
                encryptedText += matrix[matrixLength - j - 1][i]; // вверх
            }
        }

        return encryptedText;
    }

    public String decrypted(String text, int key) {
        String decryptedText = "";

        char[] arrayText = text.toCharArray();
        int matrixLength = (int) Math.sqrt(arrayText.length) + 1;
        char[][] matrix = new char[matrixLength][matrixLength];

        int n = 0;
        for (int i = 0; i < matrixLength / 2 + 1; i++) {
            for (int j = i; j < matrixLength - i; j++) {
                matrix[i][j] = arrayText[n]; // вправо
                n++;
            }
            for (int j = i + 1; j < matrixLength - i; j++) {
                matrix[j][matrixLength - i - 1] = arrayText[n]; // вниз
                n++;
            }
            for (int j = i + 1; j < matrixLength - i - 1; j++) {
                matrix[matrixLength - i - 1][matrixLength - j - 1] = arrayText[n]; // влево
                n++;
            }
            for (int j = i + 1; j < matrixLength - i - 1; j++) {
                matrix[matrixLength - j - 1][i] = arrayText[n]; // вверх
                n++;
            }
        }

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                decryptedText += matrix[i][j];


        return decryptedText;
    }
    
}
