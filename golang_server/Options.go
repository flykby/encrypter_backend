package main

import (
	"RGR/Crypto"
)

const (
	Viginere = iota + 5
	PlayFair
	Hill
	Affine
)

func Encrypt(text, key string, numcrypt int) string {
	switch numcrypt {
	case Viginere:
		return encodings.EncryptVigenere(text, key)
	case PlayFair:
		return encodings.EncryptPlayfair(text, key)
	case Hill:
		return encodings.EncryptHill(text, key)
	case Affine:
		return encodings.EncryptAffine(text, key)
	}
	
	return "error"
}

func Decrypt(text, key string, numcrypt int) string {
	switch numcrypt {
	case Viginere:
		return encodings.DecryptVigenere(text, key)
	case PlayFair:
		return encodings.DecryptPlayfair(text, key)
	case Hill:
		return encodings.DecryptHill(text, key)
	case Affine:
		return encodings.DecryptAffine(text, key)
	}
	return "error"
}