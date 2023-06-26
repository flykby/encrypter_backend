package ru.flykby.rgr.java_server.service;


import org.springframework.stereotype.Service;

import ru.flykby.rgr.java_server.service.encryption.Atbash;
import ru.flykby.rgr.java_server.service.encryption.Caesar;
import ru.flykby.rgr.java_server.service.encryption.Encryption;
import ru.flykby.rgr.java_server.service.encryption.Polybium;
import ru.flykby.rgr.java_server.service.encryption.Scytala;

import ru.flykby.rgr.java_server.exception.InvalidInputException;


@Service
public class Cryptographer {
    private Encryption encryption;
    
    public String encryption(int numberEncrypt, String text, int key) throws InvalidInputException {

        if (1 < numberEncrypt && numberEncrypt > 4 || key < 0) {
            throw new InvalidInputException("Невернный ввод");
        }

        switch (numberEncrypt) {
            case 1: {
                encryption = new Atbash();
                break;
            }
            case 2: {
                encryption = new Caesar();
                break;
            }
            case 3: {
                encryption = new Polybium();
                break;
            }
            case 4: {
                encryption = new Scytala();
                break;
            }
            default: {
                return "Cann't encrypted";
            }
        }
        return encryption.encrypted(text, key);
    }
    
    public String decryption(int numberEncrypt, String text, int key) throws InvalidInputException {

        if (1 < numberEncrypt && numberEncrypt > 4 || key < 0) {
            throw new InvalidInputException("Невернный ввод");
        }

        switch (numberEncrypt) {
            case 1: {
                encryption = new Atbash();
                break;
            }
            case 2: {
                encryption = new Caesar();
                break;
            }
            case 3: {
                encryption = new Polybium();
                break;
            }
            case 4: {
                encryption = new Scytala();
                break;
            }
            default: {
                return "Cann't encrypted";
            }
        }
        return encryption.decrypted(text, key);
    }
    
}
