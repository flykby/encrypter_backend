package server

import (
	"encoding/json"
	"fmt"
	"io"
	"net/http"
)

type Req struct {
	Text          string `json:"message"`
	Key           string `json:"key"`
	NumberEncrypt int    `json:"numberEncrypt"`
}

func Start() {
	http.HandleFunc("/encoding", ChooseEncrypt)
	http.HandleFunc("/decoding", ChooseDecrypt)
	fmt.Println("Server is started")
	http.ListenAndServe("0.0.0.0:3002", nil)
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
