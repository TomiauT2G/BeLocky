package com.example.belocky;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class ManejoEncriptacion {
    String SKLocal = "423BCEB72B23F2FD1B49BD044BB03FD995A9E90566708A22785447FA00FC0382";
    byte[] keyBytes = hexToBytes(SKLocal);
    SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
    public String desencriptarLO(String Texto) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] datosDesencriptados = cipher.doFinal(Base64.getDecoder().decode(Texto));
        return new String(datosDesencriptados);
    }
    public String encriptarLO(String Texto) throws Exception {
        Cipher Encriptar = Cipher.getInstance("AES");
        Encriptar.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] datosEncriptados = Encriptar.doFinal(Texto.getBytes());
        return Base64.getEncoder().encodeToString(datosEncriptados);
    }

     public String desencriptar(String ContrasenaEncriptada, SecretKey clave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, clave);
        byte[] datosDesencriptados = cipher.doFinal(Base64.getDecoder().decode(ContrasenaEncriptada));
        return new String(datosDesencriptados);
    }
    public String encriptar(String Contraseña, SecretKey clave) throws Exception {
        Cipher Encriptar = Cipher.getInstance("AES");
        Encriptar.init(Cipher.ENCRYPT_MODE, clave);
        byte[] datosEncriptados = Encriptar.doFinal(Contraseña.getBytes());
        return Base64.getEncoder().encodeToString(datosEncriptados);
    }
    private static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] result = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            result[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return result;
    }
}
