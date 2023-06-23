package ru.flykby.rgr.java_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.flykby.rgr.java_server.models.FormModel;
import ru.flykby.rgr.java_server.service.Cryptographer;

import ru.flykby.rgr.java_server.exception.InvalidInputException;

@RestController
@RequestMapping("/api0")
public class UserController {

    @Autowired
    private Cryptographer cryptographer;


    @PostMapping("/encrypted")
    public ResponseEntity<String> encrypted(@RequestBody FormModel form) {
        try {
            String message = cryptographer.encryption(form.getNumberEncrypt(), form.getMessage(), form.getKey());
            return ResponseEntity.ok(message);
        } catch (InvalidInputException e) {
            return ResponseEntity.badRequest().body("Ой! Кажется неверно заполнены поля");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ой! Кажется неизвестная ошибка");
        }
    }

    @PostMapping("/decrypted")
    public ResponseEntity<String> decrypted(@RequestBody FormModel form) {
        try {
            String message = cryptographer.decryption(form.getNumberEncrypt(), form.getMessage(), form.getKey());
            return ResponseEntity.ok(message);
        } catch (InvalidInputException e) {
            return ResponseEntity.badRequest().body("Ой! Кажется неверно заполнены поля");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
    
    @GetMapping("/test")
    public ResponseEntity<String> getResponse() {
        try {
            return ResponseEntity.ok("Сервер работает");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ой! Кажется ошибка");
        }
    }

}
