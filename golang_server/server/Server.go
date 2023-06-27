package server

import (
	"encoding/json"
	"fmt"
	"io"
	"net/http"
)

const(
	HOST = "0.0.0.0"
	PORT = "3002"
)

type Req struct {
	Text          string `json:"message"`
	Key           string `json:"key"`
	NumberEncrypt int    `json:"numberEncrypt"`
}

func Start() {
	http.HandleFunc("/api0/encrypted", ChooseEncrypt)
	http.HandleFunc("/api0/decrypted", ChooseDecrypt)
	fmt.Println("Server is started")
	http.ListenAndServe(HOST+":"+PORT, nil)
}

func GetInfo(w http.ResponseWriter, r *http.Request) (Req, error) {
	var info Req
	body, err := io.ReadAll(r.Body)

	if err != nil {
		return info, err
	}
	err = json.Unmarshal(body, &info)
	if err != nil {
		return info, err
	}
	return info, nil
}

func ErrorHandler(w http.ResponseWriter, err error) {
	http.Error(w, err.Error(), http.StatusInternalServerError)
}

func ChooseEncrypt(w http.ResponseWriter, r *http.Request) {
	info, err := GetInfo(w, r)
	if err != nil {
		ErrorHandler(w, err)
		return
	}
	fmt.Fprint(w, Encrypt(info.Text, info.Key, info.NumberEncrypt))
}

func ChooseDecrypt(w http.ResponseWriter, r *http.Request) {
	info, err := GetInfo(w, r)
	if err != nil {
		ErrorHandler(w, err)
		return
	}
	fmt.Fprint(w, Decrypt(info.Text, info.Key, info.NumberEncrypt))
}
