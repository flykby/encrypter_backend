package encodings

import (
	"strings"
)

const (
	KEY     = 1
	MESSAGE = 2
)

func updateinfoPlayFair(info string, def int) string {
	info = strings.ToUpper(info)
	info = strings.ReplaceAll(info, " ", "")
	info = strings.ReplaceAll(info, "J", "I")
	if def == MESSAGE {
		info = removeDouble(info)
		info = addPadding(info)
	}
	if def == KEY {
		info = removeDuplicates(info)
	}
	return info
}

func CheckPlayFair(message, key string) string {
	if !IsLetter(message) {
		return "Wrong message. Сообщение содержит цифры или спец.символы"
	}
	if !IsLetter(key) {
		return "Wrong key. Ключ содержит цифры или спец.символы"
	}
	return ""
}

// Зашифровка
func EncryptPlayfair(message, key string) string {
	key = updateinfoPlayFair(key, KEY)
	message = updateinfoPlayFair(message, MESSAGE)
	err := CheckPlayFair(message, key)
	if err != "" {
		return err
	}

	keymatrix := generateKeyMatrix(key)
	encrypted := ""
	for i := 0; i < len(message); i += 2 {
		a := message[i]
		b := message[i+1]
		row1, col1 := getPosition(keymatrix, rune(a))
		row2, col2 := getPosition(keymatrix, rune(b))
		if row1 == row2 {
			col1 = (col1 + 1) % 5
			col2 = (col2 + 1) % 5
		} else if col1 == col2 {
			row1 = (row1 + 1) % 5
			row2 = (row2 + 1) % 5
		} else {
			col1, col2 = col2, col1
		}
		encrypted += string(keymatrix[row1][col1]) + string(keymatrix[row2][col2])
	}
	return encrypted
}

// Расшифровка
func DecryptPlayfair(message, key string) string {
	key = updateinfoPlayFair(key, KEY)
	message = updateinfoPlayFair(message, MESSAGE)
	err := CheckPlayFair(message, key)
	if err != "" {
		return err
	}

	keymatrix := generateKeyMatrix(key)
	decrypted := ""
	for i := 0; i < len(message); i += 2 {
		a := message[i]
		b := message[i+1]

		row1, col1 := getPosition(keymatrix, rune(a))
		row2, col2 := getPosition(keymatrix, rune(b))

		if row1 == row2 {
			col1 = (col1 - 1 + 5) % 5
			col2 = (col2 - 1 + 5) % 5
		} else if col1 == col2 {
			row1 = (row1 - 1 + 5) % 5
			row2 = (row2 - 1 + 5) % 5
		} else {
			col1, col2 = col2, col1
		}
		decrypted += string(keymatrix[row1][col1]) + string(keymatrix[row2][col2])
	}
	decrypted = removePadding(decrypted)
	decrypted = addDouble(decrypted)
	return decrypted
}

// Создание матрицы 5x5
func generateKeyMatrix(key string) [][]rune {
	keymatrix := make([][]rune, 5)
	for i := range keymatrix {
		keymatrix[i] = make([]rune, 5)
	}
	used := make(map[rune]bool)
	row := 0
	col := 0
	for _, c := range key {
		if !used[c] {
			keymatrix[row][col] = c
			used[c] = true
			col++
			if col == 5 {
				row++
				col = 0
			}
		}
	}
	for c := 'A'; c <= 'Z'; c++ {
		if c == 'J' {
			continue
		}
		if !used[c] {
			keymatrix[row][col] = c
			col++
			if col == 5 {
				row++
				col = 0
			}
		}
	}
	return keymatrix
}

// Нахождение индекса искаемого элемента
func getPosition(keymatrix [][]rune, c rune) (int, int) {
	for i, row := range keymatrix {
		for j, v := range row {
			if v == c {
				return i, j
			}
		}
	}
	return -1, -1
}

// Удаление дубликатов у ключа
func removeDuplicates(s string) string {
	result := ""
	used := make(map[rune]bool)
	for _, c := range s {
		if !used[c] {
			result += string(c)
			used[c] = true
		}
	}
	return result
}

// Добавление символа для четности
func addPadding(s string) string {
	if len(s)%2 == 1 {
		s += "X"
	}
	return s
}

// Удаление дубликтов у сообщения
func removeDouble(s string) string {
	result := ""
	for _, c := range s {
		if result != "" {
			if string(result[len(result)-1]) == string(c) {
				result += string("X")
			}
		}
		result += string(c)
	}
	return result
}

// Добавление дубликатов у сообщения
func addDouble(s string) string {
	result := ""
	for _, c := range s {
		if string(c) == "X" {
			continue
		}
		result += string(c)
	}
	return result
}

// Удаление символа для четных отшифрованных сообщений
func removePadding(s string) string {
	if len(s)%2 == 0 && s[len(s)-1] == 'X' {
		s = s[:len(s)-1]
	}
	return s
}
