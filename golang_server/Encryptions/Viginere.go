package encodings

import (
	"strings"
)

// Зашифровка
func EncryptVigenere(message, key string) string {
	key = strings.ReplaceAll(key, " ", "")
	if strings.Contains(key, " ") {
		return "Wrong input key"
	}

	if !IsLetter(key) {
		return "Wrong input key"
	}

	keyrune := []rune(key)
	encrypted := ""
	keyIndex := 0
	for _, c := range message {
		if isAlpha(c) {
			base := byte('a')
			if isUpper(c) {
				base = 'A'
			}
			shift := toLower(keyrune[keyIndex]) - 'a'
			encryptedChar := byte((c-rune(base)+rune(shift))%26 + rune(base))
			encrypted += string(encryptedChar)
			keyIndex = (keyIndex + 1) % len(key)
		} else {
			encrypted += string(c)
		}
	}
	return encrypted
}

// Расшифровка
func DecryptVigenere(message string, key string) string {
	key = strings.ReplaceAll(key, " ", "")
	if strings.Contains(key, " ") {
		return "Wrong input key"
	}
	if !IsLetter(key) {
		return "Wrong input key"
	}

	keyrune := []rune(key)
	decrypted := ""
	keyIndex := 0
	for _, c := range message {
		if isAlpha(c) {
			base := byte('a')
			if isUpper(c) {
				base = 'A'
			}
			shift := toLower(keyrune[keyIndex]) - 'a'
			decryptedChar := byte((c-rune(base)-rune(shift)+26)%26 + rune(base))
			decrypted += string(decryptedChar)
			keyIndex = (keyIndex + 1) % len(key)
		} else {
			decrypted += string(c)
		}
	}
	return decrypted
}

// Функция для проверки, является ли символ буквой алфавита
func isAlpha(c rune) bool {
	return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')
}

// Функция для проверки, является ли символ заглавной буквой
func isUpper(c rune) bool {
	return c >= 'A' && c <= 'Z'
}

// Функция для преобразования символа в нижний регистр
func toLower(c rune) byte {
	return byte(strings.ToLower(string(c))[0])
}
