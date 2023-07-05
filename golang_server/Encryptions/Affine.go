package encodings

import (
	"math"
	"strings"
)
func CheckAffine(keyslice []int) string {
	rule := []int{2, 4, 6, 8, 10, 12, 13, 14, 16, 18, 20, 22, 23, 24, 25}
	for _, x := range rule {
		if keyslice[0] == x {
			return "Wrong input key"
		}
	}
	return ""
}

// Функция для шифрования сообщения
func EncryptAffine(message, key string) string {
	message = strings.ReplaceAll(message, " ", "")

	keyslice := invert(key)
	err := CheckAffine(keyslice)
	if err != "" {
		return err
	}

	encryptedMessage := ""
	for _, c := range message {
		if isAlpha(c) {
		encryptedChar := rune(math.Mod(float64(int(c-rune('a'))*keyslice[0]+keyslice[1]), 26))
		encryptedMessage += string(encryptedChar + rune('a'))
	}
}
	return encryptedMessage
}

// Функция для дешифрования сообщения
func DecryptAffine(message, key string) string {
	message = strings.ReplaceAll(message, " ", "")

	keyslice := invert(key)

	err := CheckAffine(keyslice)
	if err != "" {
		return err
	}

	decryptedMessage := ""
	inverseA := inverseElement(keyslice[0], 26)
	if inverseA == 0 {
		return "Wrong key. Второй ключ не подходит"
	}
	for _, c := range message {
		if isAlpha(c) {
		decryptedChar := rune(math.Mod(float64((int((c-rune('a')))-keyslice[1])*inverseA), 26))
		decryptedMessage += string(decryptedChar + rune('a'))
	}
}
	return decryptedMessage
}

// Функция для нахождения обратного элемента в кольце вычетов
func inverseElement(a int, m int) int {
	for i := 1; i < m; i++ {
		if (a*i)%m == 1 {
			return i
		}
	}
	return 0
}
