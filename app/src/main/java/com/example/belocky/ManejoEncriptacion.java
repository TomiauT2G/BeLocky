package com.example.belocky;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.Base64;

public class ManejoEncriptacion {
    private static String desencriptar(String ContrasenaEncriptada, SecretKey clave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, clave);
        byte[] datosDesencriptados = cipher.doFinal(Base64.getDecoder().decode(ContrasenaEncriptada));
        return new String(datosDesencriptados);
    }
    private static String encriptar(String Contraseña, SecretKey clave) throws Exception {
        Cipher Encriptar = Cipher.getInstance("AES");
        Encriptar.init(Cipher.ENCRYPT_MODE, clave);
        byte[] datosEncriptados = Encriptar.doFinal(Contraseña.getBytes());
        return Base64.getEncoder().encodeToString(datosEncriptados);
    }
}
