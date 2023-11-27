package com.example.belocky;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.Base64;

public class Desencriptacion {
    private static String desencriptar(String mensajeEncriptado, SecretKey clave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, clave);
        byte[] datosDesencriptados = cipher.doFinal(Base64.getDecoder().decode(mensajeEncriptado));
        return new String(datosDesencriptados);
    }
}
