package encodings

import (
	"math"
	"strings"
)

// Функция для шифрования сообщения
func EncryptAffine(message, key string) string {
	keyslice := invert(key)
	message = prepareMessage(message)
	encryptedMessage := ""
	for i := 0; i < len(message); i++ {
		encryptedChar := int(math.Mod(float64((int(message[i])-'a')*keyslice[0]+keyslice[1]), 26))
		encryptedMessage += string(encryptedChar + 'a')
	}
	return encryptedMessage
}

// Функция для дешифрования сообщения
func DecryptAffine(message, key string) string {
	keyslice := invert(key)
	message = prepareMessage(message)
	decryptedMessage := ""
	inverseA := inverseElement(keyslice[0], 26)
	for i := 0; i < len(message); i++ {
		decryptedChar := int(math.Mod(float64((int(message[i])-'a'-keyslice[1])*inverseA), 26))
		decryptedMessage += string(decryptedChar + 'a')
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
	panic("Inverse element not found")
}

// Функция для подготовки сообщения
func prepareMessage(message string) string {
	message = strings.ReplaceAll(message, " ", "")
	return message
}