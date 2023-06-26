package ru.flykby.rgr.java_server.service.encryption;


public class Scytala implements Encryption {
    
    public String encrypted(String text, int key) {
        // Убедимся, что ключ больше 0
        if (key <= 0) {
            throw new IllegalArgumentException("Ключ должен быть положительным числом");
        }

        StringBuilder sb = new StringBuilder(text);

        // Добавляем пробелы в конец сообщения, чтобы его длина была кратна ключу
        while (sb.length() % key != 0) {
            sb.append(" ");
        }

        char[][] matrix = new char[key][sb.length() / key];

        // Заполняем матрицу символами из сообщения
        int index = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][i] = sb.charAt(index++);
            }
        }

        // Считываем зашифрованное сообщение из матрицы
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result.append(matrix[i][j]);
            }
        }

        return result.toString();
    }

    public String decrypted(String text, int key) {
        if (key <= 0) {
            throw new IllegalArgumentException("Ключ должен быть положительным числом");
        }

        StringBuilder sb = new StringBuilder(text);

        char[][] matrix = new char[key][sb.length() / key];

        // Заполняем матрицу символами из сообщения
        int index = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = sb.charAt(index++);
            }
        }

        // Считываем расшифрованное сообщение из матрицы
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result.append(matrix[j][i]);
            }
        }

        return result.toString().trim();
    }
}
