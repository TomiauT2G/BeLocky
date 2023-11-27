package com.example.belocky;
import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encriptacion {
    private static String encriptar(String Contraseña, SecretKey clave) throws Exception {
        Cipher Encriptar = Cipher.getInstance("AES");
        Encriptar.init(Cipher.ENCRYPT_MODE, clave);
        byte[] datosEncriptados = Encriptar.doFinal(Contraseña.getBytes());
        return Base64.getEncoder().encodeToString(datosEncriptados);
    }
}
